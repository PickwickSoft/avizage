import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { Category, ICategory } from '../category/category.model';
import { IProduct } from './product.model';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/product');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllCategories(): Observable<HttpResponse<ICategory[]>> {
    return this.http.get<ICategory[]>(`${this.resourceUrl}/product/categories`, { observe: 'response' });
  }

  getProductById(id: String): Observable<HttpResponse<IProduct>> {
    return this.http.get<IProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  createCategory(category: Category): Observable<HttpResponse<Category>> {
    return this.http.post<Category>(`${this.resourceUrl}/product/categories`, category, { observe: 'response' });
  }
}
