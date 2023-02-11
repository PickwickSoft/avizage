import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../core/config/application-config.service';
import { IReport } from '../../entities/report/report.model';

@Injectable({ providedIn: 'root' })
export class ReportService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/shop/sales');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getTodayReport(): Observable<HttpResponse<IReport>> {
    return this.http.get<IReport>(`${this.resourceUrl}/today`, { observe: 'response' });
  }

  shareTodayReport(): Observable<number> {
    return this.http.post<number>(`${this.resourceUrl}/today/share`, { observe: 'response' });
  }
}
