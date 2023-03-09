import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user-management.model';
import { UserManagementDeleteDialogComponent } from '../delete/user-management-delete-dialog.component';
import { Account } from '../../../core/auth/account.model';
import { AccountService } from 'app/core/auth/account.service';
import { MatDialogService } from '../../../shared/dialog/mat-dialog.service';
import { UserManagementService } from '../service/user-management.service';

@Component({
  selector: 'jhi-user-mgmt-detail',
  templateUrl: './user-management-detail.component.html',
})
export class UserManagementDetailComponent implements OnInit {
  user: User | null = null;
  currentAccount: Account | null = null;

  constructor(
    private route: ActivatedRoute,
    private dialogService: MatDialogService,
    private router: Router,
    private accountService: AccountService,
    private userService: UserManagementService
  ) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => (this.currentAccount = account));
    this.route.data.subscribe(({ user }) => {
      this.user = user;
    });
  }

  deleteUser(user: User): void {
    const dialog = this.dialogService.openDialog(UserManagementDeleteDialogComponent, {
      data: user,
    });
    dialog.closed?.subscribe(reason => {
      if (reason === 'deleted') {
        this.userService.delete(user.login!).subscribe(() => {
          this.router.navigate(['../../']);
        });
      }
    });
  }
}
