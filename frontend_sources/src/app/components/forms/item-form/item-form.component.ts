import { Router } from "@angular/router";
import { Component, Input, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Location } from "@angular/common";

import { ItemsService } from "src/app/services/api/items.service";
import { CategoriesService } from "src/app/services/api/categories.service";
import { DialogService } from "src/app/services/dialog.service";
import { NotificationsService } from "src/app/services/notification.service";
import { FilesService } from "src/app/services/api/files.service";

import { ICategory, IItem, ISubcategory } from "src/app/models";

@Component({
  selector: "app-item-form",
  templateUrl: "./item-form.component.html",
  styleUrls: ["./item-form.component.scss"],
})
export class ItemFormComponent implements OnInit {
  @Input() itemId: number;
  @Input() isEdit = false;

  itemData: IItem;
  availableCategories: ICategory[] = [];

  get availableSubcategories() {
    const currentCategory = this.availableCategories.find((cat) => cat.id === this.form.controls["category"].value);
    return currentCategory?.subcategories;
  }

  form: FormGroup = new FormGroup({
    title: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required]),
    category: new FormControl({}, []),
    subcategory: new FormControl({}, []),
    images: new FormControl([]),
  });

  images: any[] = [];
  imageURLs: string[] = [];
  favoriteImageIndex = 0;

  constructor(
    private location: Location,
    private itemsService: ItemsService,
    private categoriesService: CategoriesService,
    private router: Router,
    private dialogService: DialogService,
    private notificationsService: NotificationsService,
    private filesService: FilesService
  ) {}

  async ngOnInit(): Promise<void> {
    this.availableCategories = await this.categoriesService.getAll();

    if (this.isEdit) {
      try {
        const response = await this.itemsService.getOne(this.itemId);

        this.itemData = response;

        const { title, description, classification, photos } = response;

        this.form.controls["title"].setValue(title);
        this.form.controls["description"].setValue(description);
        this.form.controls["category"].setValue(classification.category.id);
        this.form.controls["subcategory"].setValue(classification.subcategory.id);

        this.images = photos;
        this.imageURLs = photos.map((e) => e.url);
      } catch (error) {
        this.location.back();
      }
    }
  }

  selectImage(event: any) {
    const files = Array.from((event.target as HTMLInputElement).files);

    const updatedImagesValue = [...this.form.get("images").value, ...files];

    this.form.patchValue({
      images: updatedImagesValue,
    });
    this.form.get("images").updateValueAndValidity();

    const reader = new FileReader();
    reader.onload = () => {
      this.imageURLs.push(reader.result as string);
    };

    files.forEach((file) => {
      reader.readAsDataURL(file);
    });
  }

  changeFavIndex(event: number) {
    this.favoriteImageIndex = event;
  }

  goBack() {
    if (this.isEdit) {
      this.router.navigate(["/category/" + this.itemData.classification.category.id]);
    } else {
      this.location.back();
    }
  }

  removeImage(event: number) {
    if (this.favoriteImageIndex === event) {
      this.favoriteImageIndex = 0;
    }

    const filteredImagesArray = Array.from(this.form.get("images").value).filter((e, index) => index !== event);

    this.form.patchValue({
      images: filteredImagesArray,
    });

    if (event > this.images.length - 1) {
      const fileFormValue = this.form.controls["images"].value;
      const fireIndex = event - this.images.length;
      this.form.controls["images"].setValue(fileFormValue.filter((e: any, index: number) => index !== fireIndex));
    }

    this.images = this.images.filter((e, index) => index !== event);
    this.imageURLs = this.imageURLs.filter((e, index) => index !== event);
  }

  async uploadImages(imagesForUpload: File[]): Promise<any[]> {
    const imagesPrepared: any[] = [];

    for (const imgFile of imagesForUpload) {
      const response = await this.filesService.pushFileToStorage(imgFile);
      imagesPrepared.push(response);
    }

    return imagesPrepared;
  }

  async saveItem() {
    const title = this.form.controls["title"].value;
    const description = this.form.controls["description"].value;
    const category = this.availableCategories.find(
      (cat) => cat.id === this.form.controls["category"].value
    ) as ICategory;
    let subcategory = category.subcategories.find(
      (subcat) => subcat.id === this.form.controls["subcategory"].value
    ) as ISubcategory;

    if (!subcategory) {
      subcategory = { id: 0, name: "subcategory", categoryId: 0 };
    }

    const imagesForUpload = this.form.controls["images"].value;
    const imagesPrepared: any[] = await this.uploadImages(imagesForUpload);

    if (this.isEdit) {
      const response = await this.itemsService.update(this.itemId, {
        ...this.itemData,
        title,
        description,
        classification: {
          category,
          subcategory,
        },
        photos: [...this.images, ...imagesPrepared],
      });

      this.notificationsService.openSnackBar("Item updated", 2000);
      this.router.navigate(["/item/" + response.id]);
    } else {
      const response = await this.itemsService.create({
        id: 1234,
        title,
        description,
        classification: {
          category,
          subcategory,
        },
        quality: {
          value: "100",
        },
        photos: [...imagesPrepared],
        videos: [],
      });

      this.notificationsService.openSnackBar("Item created", 2000);
      this.router.navigate(["/item/" + response.id]);
    }
  }

  async confirmDeleteAction() {
    const response = await this.dialogService.showConfirm(`Delete ${this.form.controls["title"].value} item?`);

    if (response) {
      await this.itemsService.delete(this.itemId);
      this.router.navigate(["/category/" + this.itemData.classification.category.id]);
    }
  }
}
