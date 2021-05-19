import { Component, Input, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Location } from "@angular/common";
import { Router } from "@angular/router";

import { CategoriesService } from "src/app/services/api/categories.service";
import { NotificationsService } from "src/app/services/notification.service";
import { DialogService } from "src/app/services/dialog.service";

@Component({
  selector: "app-category-form",
  templateUrl: "./category-form.component.html",
  styleUrls: ["./category-form.component.scss"],
})
export class CategoryFormComponent implements OnInit {
  @Input() isEdit = false;
  @Input() entityId: number;

  form: FormGroup = new FormGroup({
    name: new FormControl("", [Validators.required]),
  });

  constructor(
    private location: Location,
    private categoriesService: CategoriesService,
    private notificationsService: NotificationsService,
    private router: Router,
    private dialogService: DialogService
  ) {}

  goBack() {
    this.router.navigate(["/admin"]);
  }

  async ngOnInit(): Promise<void> {
    if (this.isEdit) {
      try {
        const categoryData = await this.categoriesService.getOne(this.entityId);

        this.form.controls["name"].setValue(categoryData.name);
      } catch (error) {
        this.location.back();
        this.notificationsService.openSnackBar("Category loading failed");
      }
    }
  }

  async saveCategory(): Promise<void> {
    if (this.isEdit) {
      await this.categoriesService.update(this.entityId, {
        id: this.entityId,
        name: this.form.controls["name"].value,
      });
      this.notificationsService.openSnackBar("Category updated", 2000);
    } else {
      const response = await this.categoriesService.create({
        id: 1234,
        name: this.form.controls["name"].value,
      });
      console.log(response);
      this.notificationsService.openSnackBar("Category created", 2000);
    }

    this.router.navigate(["/admin"]);
  }

  async confirmDeleteAction() {
    const response = await this.dialogService.showConfirm(`Delete ${this.form.controls["name"].value} category?`);

    if (response) {
      await this.categoriesService.delete(this.entityId);
      this.router.navigate(["/admin"]);
    }
  }
}
