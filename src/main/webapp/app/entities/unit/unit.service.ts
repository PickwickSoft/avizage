import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { IUnit } from './unit.model';

@Injectable({ providedIn: 'root' })
export class UnitService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/unit');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllUnits(): Observable<HttpResponse<IUnit[]>> {
    return this.http.get<IUnit[]>(this.resourceUrl, { observe: 'response' });
  }
}
