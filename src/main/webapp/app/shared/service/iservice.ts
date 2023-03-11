import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pagination, SearchWithPagination } from '../../core/request/request.model';

@Injectable({ providedIn: 'root' })
export abstract class IService<T> {
  abstract query(): Observable<HttpResponse<T[]>>;
  abstract query(req?: Pagination): Observable<HttpResponse<T[]>>;
  abstract query(req?: SearchWithPagination): Observable<HttpResponse<T[]>>;
}
