import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { LANGUAGES } from 'app/config/language.constants';
import { User } from '../user-management.model';
import { UserManagementService } from '../service/user-management.service';

const initialUser: User = {
  langKey: 'en',
};

@Component({
  selector: 'jhi-user-mgmt-update',
  templateUrl: './user-management-update.component.html',
})
export class UserManagementUpdateComponent implements OnInit {
  languages = LANGUAGES;
  authorities: string[] = [];
  isSaving = false;
  id: number | null = null;

  editForm = new FormGroup({
    firstName: new FormControl(initialUser.firstName, { validators: [Validators.maxLength(50)] }),
    lastName: new FormControl(initialUser.lastName, { validators: [Validators.maxLength(50)] }),
    email: new FormControl(initialUser.email, {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email],
    }),
    activated: new FormControl(initialUser.activated, { nonNullable: true }),
    langKey: new FormControl(initialUser.langKey, { nonNullable: true }),
    authorities: new FormControl(initialUser.authorities, { nonNullable: true }),
  });

  constructor(private userService: UserManagementService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ user }) => {
      if (user) {
        if (user.id === undefined) {
          user.activated = true;
        } else {
          this.id = user.id;
        }
        this.editForm.patchValue(user);
      }
    });
    this.userService.authorities().subscribe(authorities => (this.authorities = authorities));
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const user = this.editForm.getRawValue();
    if (this.id !== null && this.id !== undefined) {
      this.userService
        .update({
          id: this.id,
          login: user.email,
          email: user.email,
          firstName: user.firstName,
          lastName: user.lastName,
          langKey: user.langKey,
          authorities: user.authorities,
          activated: user.activated,
        })
        .subscribe({
          next: () => this.onSaveSuccess(),
          error: () => this.onSaveError(),
        });
    } else {
      this.userService
        .create({
          login: user.email,
          email: user.email,
          firstName: user.firstName,
          lastName: user.lastName,
          langKey: user.langKey,
          authorities: user.authorities,
          activated: user.activated,
        })
        .subscribe({
          next: () => this.onSaveSuccess(),
          error: () => this.onSaveError(),
        });
    }
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
