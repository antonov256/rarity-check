import { Component, Input, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Location } from "@angular/common";
import { Router } from "@angular/router";

import { CategoriesService } from "src/app/services/api/categories.service";
import { SubcategoriesService } from "src/app/services/api/subcategories.service";
import { NotificationsService } from "src/app/services/notification.service";
import { DialogService } from "src/app/services/dialog.service";

import { ICategory } from "src/app/models";

@Component({
  selector: "app-subcategory-form",
  templateUrl: "./subcategory-form.component.html",
  styleUrls: ["./subcategory-form.component.scss"],
})
export class SubcategoryFormComponent implements OnInit {
  @Input() isEdit = false;
  @Input() entityId: number;
  @Input() isSubcategory = false;

  form: FormGroup = new FormGroup({
    name: new FormControl("", [Validators.required]),
    parentCategory: new FormControl("", [Validators.required]),
  });

  categories: ICategory[];

  constructor(
    private location: Location,
    private categoriesService: CategoriesService,
    private subcategoriesService: SubcategoriesService,
    private notificationsService: NotificationsService,
    private router: Router,
    private dialogService: DialogService
  ) {}

  goBack() {
    this.router.navigate(["/admin"]);
  }

  async ngOnInit(): Promise<void> {
    this.categories = await this.categoriesService.getAll();

    if (this.isEdit) {
      try {
        const subcategoryData = await this.subcategoriesService.getOne(this.entityId);

        this.form.controls["name"].setValue(subcategoryData.name);
        this.form.controls["parentCategory"].setValue(subcategoryData.categoryId);
      } catch (error) {
        this.location.back();
        this.notificationsService.openSnackBar("Subcatedory loading failed");
      }
    }
  }

  async saveSubcategory(): Promise<void> {
    if (this.isEdit) {
      await this.subcategoriesService.update(this.entityId, {
        id: this.entityId,
        categoryId: this.form.controls["parentCategory"].value,
        name: this.form.controls["name"].value,
      });
      this.notificationsService.openSnackBar("Subcategory updated", 2000);
    } else {
      const response = await this.subcategoriesService.create({
        id: 1234,
        categoryId: this.form.controls["parentCategory"].value,
        name: this.form.controls["name"].value,
      });
      console.log(response);
      this.notificationsService.openSnackBar("Subcategory created", 2000);
    }

    this.router.navigate(["/admin"]);
  }

  async confirmDeleteAction() {
    const response = await this.dialogService.showConfirm(`Delete ${this.form.controls["name"].value} subcategory?`);

    if (response) {
      await this.subcategoriesService.delete(this.entityId);
      this.router.navigate(["/admin"]);
    }
  }
}
