import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Component, OnInit } from "@angular/core";

import { SessionService } from "src/app/services/session.service";
import { ProfileService } from "src/app/services/api/profile.service";
import { NotificationsService } from "src/app/services/notification.service";

import passwordMatchesValidator from "src/app/utils/validators/passwordMatches";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.scss"],
})
export class ProfileComponent implements OnInit {
  dataForm = new FormGroup({
    name: new FormControl(this.sessionService.userData.name, [Validators.pattern("[A-Za-z0-9-_.]{2,20}")]),
    surname: new FormControl(this.sessionService.userData.surname, [Validators.pattern("[A-Za-z0-9-_.]{2,20}")]),
    // username: new FormControl("", [Validators.required, Validators.pattern("[A-Za-z0-9-_.]{5,20}")]),
    // phone: new FormControl("", [
    //   Validators.pattern(
    //     "(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))s*[)]?[-s.]?[(]?[0-9]{1,3}[)]?([-s.]?[0-9]{3})([-s.]?[0-9]{3,4})"
    //   ),
    // ]),
  });

  passwordForm = new FormGroup(
    {
      oldPassword: new FormControl("", [Validators.required]),
      newPassword: new FormControl("", [Validators.required]),
      newPasswordRepeat: new FormControl("", [Validators.required]),
    },
    { validators: [passwordMatchesValidator("newPassword", "newPasswordRepeat")], updateOn: "change" }
  );

  constructor(
    public sessionService: SessionService,
    private profileService: ProfileService,
    private notificationsService: NotificationsService
  ) {}

  hidePassword = true;
  hideNewPassword = true;
  hidePasswordRepeat = true;
  ngOnInit(): void {}

  getUsernameErrorMessage() {
    if (this.dataForm.controls["username"].invalid) {
      return "Invalid value";
    }
  }

  getNameErrorMessage() {
    if (this.dataForm.controls["name"].invalid) {
      return "Invalid value";
    }
  }

  getSurnameErrorMessage() {
    if (this.dataForm.controls["surname"].invalid) {
      return "Invalid value";
    }
  }

  // getPhoneErrorMessage() {
  //   if (this.dataForm.controls["phone"].invalid) {
  //     return "Invalid value";
  //   }
  // }

  getOldPasswordErrorMessage() {
    if (this.passwordForm.controls["oldPassword"].hasError("required")) {
      return "You must enter a value";
    }
  }

  getNewPasswordErrorMessage() {
    if (this.passwordForm.controls["newPassword"].hasError("required")) {
      return "You must enter a value";
    }
  }

  getNewPasswordRepeatErrorMessage() {
    if (this.passwordForm.controls["newPasswordRepeat"].hasError("required")) {
      return "You must enter a value";
    }

    if (this.passwordForm.hasError("passwordMatches")) {
      return "Passwords dont match";
    }
  }

  // TODO: add actions for change email & number
  async saveHandler(): Promise<void> {
    await this.profileService.changeProfileData(
      this.dataForm.controls["name"].value,
      this.dataForm.controls["surname"].value
    );
    this.notificationsService.openSnackBar("User data updated succesfuly");
  }

  async changePassword(): Promise<void> {
    await this.profileService.changePassword(
      this.passwordForm.controls["oldPassword"].value,
      this.passwordForm.controls["newPassword"].value,
      this.passwordForm.controls["newPasswordRepeat"].value
    );
    this.notificationsService.openSnackBar("Password changed succesfuly");
  }
}
