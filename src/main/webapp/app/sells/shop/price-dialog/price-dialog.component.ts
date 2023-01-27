import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ICategory } from '../../../entities/category/category.model';

@Component({
  selector: 'price-dialog',
  templateUrl: './price-dialog.component.html',
})
export class PriceDialogComponent {
  price: number = -1;
  constructor(@Inject(MAT_DIALOG_DATA) public category: ICategory) {}
}
