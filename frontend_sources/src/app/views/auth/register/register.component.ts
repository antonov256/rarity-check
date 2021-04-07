import { Component, OnInit } from "@angular/core";
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NotificationsService } from './../../../services/notification.service';
import { ApiService } from './../../../services/api.service';


@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
})
export class RegisterComponent implements OnInit {
  constructor(
    private apiService : ApiService,
    private router: Router,
    private notificationsService: NotificationsService
  ) {}

  hidePassword = true;
  hidePasswordRepeat = true;

  form: FormGroup = new FormGroup({
    fullName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    passwordRepeat: new FormControl('', [Validators.required]),
  }, { validators: [this.passwordMatchesValidator()], updateOn: 'blur'});


  ngOnInit(): void {}

  getFullNameErrorMessage(){
    if(this.form.controls['fullName'].hasError('required')){
      return 'You must enter a value';
    }

    if(this.form.controls['fullName'].invalid){
      return 'Invalid value';
    }
  }

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

  getPasswordRepeatErrorMessage(){
    if(this.form.controls['passwordRepeat'].hasError('required')){
      return 'You must enter a value';
    }
  }

  // FIX: error message that passwords is not matches
  // *ngIf dont get a error status from formGroup
  passwordMatchesValidator(): ValidatorFn {
    return (control: AbstractControl) : ValidationErrors => {

      if(control.get('password').value == control.get('passwordRepeat').value){
        return null;
      }

      return { passwordMatches: true };
    }
  }

  async registerHandler(){
    if(this.form.valid){
      try {
        const response = await this.apiService.userRegister(this.form.controls['email'].value, this.form.controls['password'].value);

        // TODO: add register actions
      } catch (error) {
        this.notificationsService.openSnackBar('Registration failed');
      }
    }
  }
}
