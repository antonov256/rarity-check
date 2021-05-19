import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export default function passwordMatchesValidator(passwordField: string, passwordRepeatField: string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {

    if (control.get(passwordField).value === control.get(passwordRepeatField).value) {
      return null;
    }

    return { passwordMatches: true };
  };
}
