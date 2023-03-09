import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../user-management.model';

@Component({
  selector: 'user-mgmt-delete-dialog',
  templateUrl: './user-management-delete-dialog.component.html',
})
export class UserManagementDeleteDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public user: User) {}
}
