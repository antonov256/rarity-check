import { MatDialog } from "@angular/material/dialog";
import { Injectable } from "@angular/core";
import { ConfirmComponent } from "../components/dialogs/confirm/confirm.component";

@Injectable({
  providedIn: "root",
})
export class DialogService {
  constructor(private dialog: MatDialog) {}

  async showConfirm(question: string): Promise<boolean> {
    const dialogRef = this.dialog.open(ConfirmComponent, {
      width: "250px",
      data: {
        question,
        yesText: "Yes",
        noText: "No",
      },
    });

    return await dialogRef.afterClosed().toPromise<boolean>();
  }
}
