import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { UserManagementService } from '../service/user-management.service';
import { User } from '../user-management.model';
import { UserManagementDeleteDialogComponent } from '../delete/user-management-delete-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, SortDirection } from '@angular/material/sort';
import { MatDialogService } from '../../../shared/dialog/mat-dialog.service';
import { SortableComponent } from '../../../shared/sort/component';

@Component({
  selector: 'jhi-user-mgmt',
  templateUrl: './user-management.component.html',
})
export class UserManagementComponent extends SortableComponent<User> implements OnInit, AfterViewInit {
  currentAccount: Account | null = null;
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'activated', 'authorities', 'actions'];
  itemsPerPage = 5;
  defaultSortColumn = 'email';
  defaultSortDirection = 'asc' as SortDirection;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    protected userService: UserManagementService,
    private accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    private dialogService: MatDialogService
  ) {
    super(activatedRoute, router, userService);
  }

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => (this.currentAccount = account));
    this.handleNavigation();
  }

  ngAfterViewInit() {
    this.data.paginator = this.paginator;
    this.data.sort = this.sort;
  }

  setActive(user: User, isActivated: boolean): void {
    this.userService.update({ ...user, activated: isActivated }).subscribe(() => this.loadAll());
  }
  deleteUser(user: User): void {
    const dialog = this.dialogService.openDialog(UserManagementDeleteDialogComponent, {
      data: user,
    });
    dialog.closed?.subscribe(reason => {
      if (reason === 'deleted') {
        this.userService.delete(user.login!).subscribe(() => {
          this.loadAll();
        });
      }
    });
  }
}
