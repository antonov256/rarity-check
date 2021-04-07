import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.scss"],
})
export class ProfileComponent implements OnInit {
  newEmail = new FormControl('', [Validators.required, Validators.email]);
  phone = new FormControl('', [Validators.required, Validators.pattern('(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\s*[)]?[-\s\.]?[(]?[0-9]{1,3}[)]?([-\s\.]?[0-9]{3})([-\s\.]?[0-9]{3,4})')]);

  constructor() {}

  hidePassword = true;
  hidePasswordRepeat = true;
  ngOnInit(): void {}

  getEmailErrorMessage(){
    if (this.newEmail.hasError('required')) {
      return 'You must enter a value';
    }

    if(this.newEmail.hasError('email')){
      return 'Not a valid email';
    }
  }

  getPhoneErrorMessage(){
    if (this.phone.hasError('required')) {
      return 'You must enter a value';
    }

    if(this.phone.hasError('patten')){
      return 'Not a telephone';
    }
  }

  // TODO: add actions for change email & number
  saveHandler(){
    if(this.newEmail.valid && this.phone.valid){
      // actions
    }
  }
}
