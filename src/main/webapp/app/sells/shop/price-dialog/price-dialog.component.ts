import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ICategory } from '../../../entities/category/category.model';

@Component({
  selector: 'price-dialog',
  templateUrl: './price-dialog.component.html',
  styleUrls: ['./price-dialog.component.scss'],
})
export class PriceDialogComponent {
  price: string = '';
  constructor(@Inject(MAT_DIALOG_DATA) public category: ICategory) {}

  parseFloat(value: string): number {
    return parseFloat(value);
  }

  priceAdd(price: string): void {
    // Only allow 2 decimal points
    if (this.price.indexOf('.') !== -1 && this.price.split('.')[1].length === 2) {
      return;
    }
    this.price = this.price + price;
  }

  priceDecimalPoint(): void {
    if (this.price.indexOf('.') === -1) {
      this.price = this.price + '.';
    }
  }

  priceDel(): void {
    this.price = this.price.slice(0, -1);
  }
}
