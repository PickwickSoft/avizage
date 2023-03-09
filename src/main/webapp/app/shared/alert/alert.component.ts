import { Directive, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AlertService, Alert } from 'app/core/util/alert.service';

@Directive({
  selector: 'jhi-alert',
})
export class AlertComponent implements OnInit, OnDestroy {
  alerts: Alert[] = [];

  constructor(private alertService: AlertService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.alerts = this.alertService.get();
    this.alerts.forEach(element => {
      if (element.message) {
        this.openSnackBar(element.message, 'Close', element.close, element.timeout ? element.timeout : -1);
      }
    });
  }

  openSnackBar(message: string, action: string, actionFunc: any, duration: number) {
    let snackBarRef = this._snackBar.open(message, action, {
      duration: duration,
    });
    snackBarRef.afterDismissed().subscribe(() => actionFunc()?.(this.alerts));
  }
  ngOnDestroy(): void {
    this.alertService.clear();
  }
}
