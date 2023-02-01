import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { IBill } from '../../../entities/bill/bill.model';
import { ICartItem } from '../../../entities/cart-item/cart-item.model';

@Component({
  selector: 'price-dialog',
  templateUrl: './checkout-dialog.component.html',
  styleUrls: ['./checkout-dialog.component.scss'],
})
export class CheckoutDialogComponent {
  cart: MatTableDataSource<ICartItem> = new MatTableDataSource<ICartItem>(this.bill.products);
  displayedColumns: string[] = ['id', 'name', 'qty', 'price'];

  constructor(@Inject(MAT_DIALOG_DATA) public bill: IBill) {}
}
