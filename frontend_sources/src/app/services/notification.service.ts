import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from "@angular/material/snack-bar";

@Injectable({
  providedIn: "root",
})
export class NotificationsService {
  constructor(private snackBar: MatSnackBar) {}

  openSnackBar(
    message: string,
    duration: number = 0,
    action?: string,
    postition?: { horizontal: MatSnackBarHorizontalPosition; vertical: MatSnackBarVerticalPosition }
  ) {
    this.snackBar.open(message, action || "Close", {
      duration,
      horizontalPosition: postition?.horizontal || "right",
      verticalPosition: postition?.vertical || "bottom",
    });
  }
}
