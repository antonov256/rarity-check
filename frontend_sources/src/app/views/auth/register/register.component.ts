import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

import { AuthService } from "./../../../services/api/auth.service";
import { NotificationsService } from "./../../../services/notification.service";

import passwordMatchesValidator from "src/app/utils/validators/passwordMatches";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
})
export class RegisterComponent implements OnInit {
  constructor(
    private router: Router,
    private authService: AuthService,
    private notificationsService: NotificationsService
  ) {}

  hidePassword = true;
  hidePasswordRepeat = true;

  form: FormGroup = new FormGroup(
    {
      username: new FormControl("", [Validators.required, Validators.pattern("[A-Za-z0-9-_.]{5,20}")]),
      fullName: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z ]*")]),
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required]),
      passwordRepeat: new FormControl("", [Validators.required]),
    },
    {
      validators: [passwordMatchesValidator("password", "passwordRepeat")],
      updateOn: "change",
    }
  );

  ngOnInit(): void {}

  getUsernameErrorMessage() {
    if (this.form.controls["username"].hasError("required")) {
      return "You must enter a value";
    }

    if (this.form.controls["username"].invalid) {
      return "Invalid value";
    }
  }

  getFullNameErrorMessage() {
    if (this.form.controls["fullName"].hasError("required")) {
      return "You must enter a value";
    }

    if (this.form.controls["fullName"].invalid) {
      return "Invalid value";
    }
  }

  getEmailErrorMessage() {
    if (this.form.controls["email"].hasError("required")) {
      return "You must enter a value";
    }

    if (this.form.controls["email"].hasError("email")) {
      return "Not a valid email";
    }
  }

  getPasswordErrorMessage() {
    if (this.form.controls["password"].hasError("required")) {
      return "You must enter a value";
    }
  }

  getPasswordRepeatErrorMessage() {
    if (this.form.controls["passwordRepeat"].hasError("required")) {
      return "You must enter a value";
    }
  }

  // FIX: error message that passwords is not matches
  // *ngIf dont get a error status from formGroup

  async registerHandler() {
    if (this.form.valid) {
      try {
        await this.authService.register(
          this.form.controls["email"].value,
          this.form.controls["fullName"].value,
          this.form.controls["password"].value,
          this.form.controls["passwordRepeat"].value,
          this.form.controls["username"].value
        );
        this.router.navigate(["/"]);
        this.notificationsService.openSnackBar("Registration completed", 1600);
      } catch (error) {}
    }
  }
}
