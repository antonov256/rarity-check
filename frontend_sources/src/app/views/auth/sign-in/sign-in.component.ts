import { NotificationsService } from './../../../services/notification.service';
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from './../../../services/auth.service';

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {

  constructor(
    private authService : AuthService,
    private notificationsService: NotificationsService,
    private router: Router
  ) {}

  hidePassword = true;

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  ngOnInit(): void {}

  getEmailErrorMessage(){
    if (this.form.controls['email'].hasError('required')) {
      return 'You must enter a value';
    }

    if(this.form.controls['email'].hasError('email')){
      return 'Not a valid email';
    }
  }

  getPasswordErrorMessage(){
    if(this.form.controls['password'].hasError('required')){
      return 'You must enter a value';
    }
  }

  async loginHandler(){
    if(this.form.valid){
      try {
        const response = await this.authService.logIn(this.form.controls['email'].value, this.form.controls['password'].value);

        // TODO: add login actions
      } catch (error) {
        this.notificationsService.openSnackBar('Registration failed');
      }
    }
  }
}
