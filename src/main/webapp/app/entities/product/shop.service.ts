import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { IProduct } from './product.model';
import { ICategory } from '../category/category.model';
import { APICartItem, ICartItem } from '../cart-item/cart-item.model';
import { IBill } from '../bill/bill.model';

@Injectable({ providedIn: 'root' })
export class ShopService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/shop');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getDefaultProducts(): Observable<HttpResponse<IProduct[]>> {
    return this.http.get<IProduct[]>(`${this.resourceUrl}/product/default`, { observe: 'response' });
  }

  getDefaultCategories(): Observable<HttpResponse<ICategory[]>> {
    return this.http.get<ICategory[]>(`${this.resourceUrl}/product/category/default`, { observe: 'response' });
  }

  checkout(cart: ICartItem[]): Observable<HttpResponse<IBill>> {
    cart = cart.map(item => new APICartItem(item.id, item.qty, item.price));
    return this.http.post<IBill>(`${this.resourceUrl}/sale`, cart, { observe: 'response' });
  }
}
