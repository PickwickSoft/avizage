import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CartItem, ICartItem } from '../../../entities/cart-item/cart-item.model';
import { IProduct } from '../../../entities/product/product.model';
import { ICategory } from '../../../entities/category/category.model';
import { MatDialogService } from '../../../shared/dialog/mat-dialog.service';
import { PriceDialogComponent } from '../price-dialog/price-dialog.component';

const demoProducts: IProduct[] = [
  { id: 1, name: 'Book 1', price: 10 },
  { id: 2, name: 'Book 2', price: 20 },
  { id: 3, name: 'Book 3', price: 30 },
  { id: 4, name: 'Book 4', price: 40 },
  { id: 5, name: 'Book 5', price: 50 },
  { id: 6, name: 'Book 6', price: 60 },
];

const demoCategories: ICategory[] = [
  { id: 1000, name: 'Category 1' },
  { id: 2000, name: 'Category 2' },
  { id: 3000, name: 'Category 3' },
  { id: 4000, name: 'Category 4' },
  { id: 5000, name: 'Category 5' },
  { id: 6000, name: 'Category 6' },
];

@Component({
  selector: 'shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss'],
})
export class ShopComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'qty', 'price'];
  cart: MatTableDataSource<ICartItem> = new MatTableDataSource<ICartItem>([]);
  products: IProduct[] = [];
  categories: ICategory[] = [];

  constructor(private dialogService: MatDialogService) {}

  ngOnInit(): void {
    this.products = demoProducts;
    this.categories = demoCategories;
  }

  getTotalCost() {
    return this.cart.data.map(t => t.qty * t.price).reduce((acc, value) => acc + value, 0);
  }

  addProductToCart(product: IProduct) {
    const cartItem = this.cart.data.find(item => item.id === product.id);
    if (cartItem) {
      cartItem.qty++;
    } else {
      this.cart.data.push(new CartItem(product.id, product.name, 1, product.price));
    }
    this.cart._updateChangeSubscription();
  }

  addProductFromCategoryToCart(category: ICategory, price: number) {
    const cartItem = this.cart.data.find(item => item.id === category.id && item.price === price);
    if (cartItem) {
      cartItem.qty++;
    } else {
      this.cart.data.push(new CartItem(category.id, category.name, 1, price));
    }
    this.cart._updateChangeSubscription();
  }

  addCategoryToCart(category: ICategory) {
    const dialog = this.dialogService.openDialog(PriceDialogComponent, { data: category });
    dialog.closed?.subscribe((price: number) => {
      if (price >= 0) {
        this.addProductFromCategoryToCart(category, price);
      }
    });
  }
}
