import { ComponentType } from '@angular/cdk/overlay';
import { Injectable } from '@angular/core';

import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MatDialogService {
  private dialogRef: any;
  public closed: Observable<any> | undefined;

  constructor(public dialog: MatDialog) {}

  openDialog(component: ComponentType<unknown>, config: MatDialogConfig<any | undefined>): MatDialogService {
    this.dialogRef = this.dialog.open(component, config);

    this.closed = this.dialogRef.afterClosed();
    return this;
  }
}
