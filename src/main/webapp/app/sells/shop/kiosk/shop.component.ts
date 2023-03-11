import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CartItem, ICartItem } from '../../../entities/cart-item/cart-item.model';
import { IProduct, Product } from '../../../entities/product/product.model';
import { ICategory } from '../../../entities/category/category.model';
import { MatDialogService } from '../../../shared/dialog/mat-dialog.service';
import { PriceDialogComponent } from '../price-dialog/price-dialog.component';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import { CheckoutDialogComponent } from '../checkout-dialog/checkout-dialog.component';
import { IBill } from '../../../entities/bill/bill.model';
import { ShopService } from '../shop.service';
import { HttpResponse } from '@angular/common/http';
import { StockService } from '../../../stock/stock.service';
import { IProductEntry } from '../../../entities/product/product-entry.model';
import { MatInput } from '@angular/material/input';

@Component({
  selector: 'shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss'],
})
export class ShopComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'qty', 'price', 'toolbar'];
  displayedColumnsMobile: string[] = ['name', 'id', 'qty', 'price', 'toolbar'];
  cart: MatTableDataSource<ICartItem> = new MatTableDataSource<ICartItem>([]);
  products: IProduct[] = [];
  productId: string = '';
  categories: ICategory[] = [];
  @ViewChild('idInput') idInput!: MatInput;

  constructor(private dialogService: MatDialogService, private shopService: ShopService, private stockService: StockService) {}

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    this.shopService.getDefaultProducts().subscribe((res: HttpResponse<IProduct[]>) => {
      this.products = res.body || [];
    });
    this.shopService.getDefaultCategories().subscribe((res: HttpResponse<ICategory[]>) => {
      this.categories = res.body || [];
    });
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
      this.cart.data.push(new CartItem(category.id!, category.name, 1, price));
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

  deleteCartItems(cartItem: ICartItem) {
    this.cart.data = this.cart.data.filter(item => item !== cartItem);
    this.cart._updateChangeSubscription();
  }

  removeOneCartItem(cartItem: ICartItem) {
    if (cartItem.qty > 1) {
      cartItem.qty--;
    } else {
      this.deleteCartItems(cartItem);
    }
    this.cart._updateChangeSubscription();
  }

  emptyCart() {
    this.cart.data = [];
    this.cart._updateChangeSubscription();
  }

  increaseOneCartItem(cartItem: ICartItem) {
    cartItem.qty++;
    this.cart._updateChangeSubscription();
  }

  checkOut() {
    this.shopService.checkout(this.cart.data).subscribe((bill: HttpResponse<IBill>) => {
      this.dialogService.openDialog(CheckoutDialogComponent, { data: bill.body });
    });
    this.emptyCart();
  }

  iconToProp(iconName?: string): IconProp {
    return iconName as IconProp;
  }

  getDataSource(): string[] {
    if (window.innerWidth < 600) {
      return this.displayedColumnsMobile;
    }
    return this.displayedColumns;
  }

  handleKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.stockService.getProductByIdAndStorageId(+this.productId, 1).subscribe((res: HttpResponse<IProductEntry>) => {
        const productEntry = res.body || null;
        if (productEntry !== null) {
          this.addProductToCart(new Product(productEntry.productId, productEntry.productName, productEntry.salePrice, ''));
        }
      });
      this.productId = '';
    }
  }

  onBlur(event: FocusEvent) {
    console.error('Out');
    this.idInput.focus();
  }
}
