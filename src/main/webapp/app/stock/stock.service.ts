import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../core/config/application-config.service';
import { IStorage } from '../entities/storage/storage.model';
import { IProductEntry } from '../entities/product/product-entry.model';

@Injectable({ providedIn: 'root' })
export class StockService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/stocks');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllStorages(): Observable<HttpResponse<IStorage[]>> {
    return this.http.get<IStorage[]>(`${this.resourceUrl}/storages`, { observe: 'response' });
  }

  getProductByIdAndStorageId(productId: number, storageId: number): Observable<HttpResponse<IProductEntry>> {
    return this.http.get<IProductEntry>(`${this.resourceUrl}/${productId}/${storageId}`, { observe: 'response' });
  }
}
