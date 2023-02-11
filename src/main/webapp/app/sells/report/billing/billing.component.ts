import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ReportService } from '../report.service';
import { IReport, IReportItem } from '../../../entities/report/report.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss'],
})
export class BillingComponent implements OnInit {
  displayedColumns: string[] = ['name', 'qty', 'price'];
  report: MatTableDataSource<IReportItem> = new MatTableDataSource<IReportItem>([]);
  total = 0;

  constructor(private reportService: ReportService) {}

  ngOnInit(): void {
    this.loadAll();
  }

  private loadAll() {
    this.reportService.getTodayReport().subscribe((res: HttpResponse<IReport>) => {
      this.report.data = res.body?.items || [];
      this.total = res.body?.total || 0;
    });
  }

  shareReport() {
    this.reportService.shareTodayReport().subscribe(() => {});
  }
}
