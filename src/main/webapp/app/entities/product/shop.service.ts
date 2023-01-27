import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { IProduct } from './product.model';
import { ICategory } from '../category/category.model';
import { ICartItem } from '../cart-item/cart-item.model';

@Injectable({ providedIn: 'root' })
export class ShopService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/shop/product');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getDefaultProducts(): Observable<HttpResponse<IProduct[]>> {
    return this.http.get<IProduct[]>(`${this.resourceUrl}/default`, { observe: 'response' });
  }

  getDefaultCategories(): Observable<HttpResponse<ICategory[]>> {
    return this.http.get<ICategory[]>(`${this.resourceUrl}/category/default`, { observe: 'response' });
  }

  sellCart(cart: ICartItem[]): Observable<HttpResponse<number>> {
    return this.http.post<number>(`${this.resourceUrl}/sale`, cart, { observe: 'response' });
  }
}
