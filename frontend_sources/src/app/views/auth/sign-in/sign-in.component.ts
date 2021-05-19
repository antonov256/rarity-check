import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

import { NotificationsService } from "./../../../services/notification.service";
import { SessionService } from "src/app/services/session.service";

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {
  constructor(
    private sessionService: SessionService,
    private notificationsService: NotificationsService,
    private router: Router
  ) {}

  hidePassword = true;

  form: FormGroup = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.pattern("[A-Za-z0-9-_.]{5,20}")]),
    password: new FormControl("", [Validators.required]),
  });

  ngOnInit(): void {}

  getUsernameErrorMessage() {
    if (this.form.controls["username"].hasError("required")) {
      return "You must enter a value";
    }

    if (this.form.controls["username"].hasError("pattern")) {
      return "Not a valid username";
    }
  }

  getPasswordErrorMessage() {
    if (this.form.controls["password"].hasError("required")) {
      return "You must enter a value";
    }
  }

  async loginHandler() {
    if (this.form.valid) {
      try {
        await this.sessionService.logIn(this.form.controls["username"].value, this.form.controls["password"].value);
        this.router.navigate(["/"]);
        this.notificationsService.openSnackBar("Logged in successfuly", 3000, "", {
          horizontal: "center",
          vertical: "bottom",
        });
      } catch (error) {}
    }
  }
}
