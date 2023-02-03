import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ReportService } from '../report.service';
import { IReport, IReportItem, Report, ReportItem } from '../../../entities/report/report.model';
import { HttpResponse } from '@angular/common/http';

const testReport = new Report(
  [
    new ReportItem(1, 'Product 1', 1, 1000),
    new ReportItem(2, 'Product 2', 2, 2000),
    new ReportItem(3, 'Product 3', 3, 3000),
    new ReportItem(4, 'Product 4', 4, 4000),
    new ReportItem(5, 'Product 5', 5, 5000),
    new ReportItem(6, 'Product 6', 6, 6000),
    new ReportItem(7, 'Product 7', 7, 7000),
    new ReportItem(8, 'Product 8', 8, 8000),
    new ReportItem(9, 'Product 9', 9, 9000),
    new ReportItem(10, 'Product 10', 10, 10000),
    new ReportItem(11, 'Product 11', 11, 11000),
  ],
  100000
);

@Component({
  selector: 'jhi-billing',
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
    this.report.data = testReport.items;
    this.total = testReport.total;
    this.reportService.getTodayReport().subscribe((res: HttpResponse<IReport>) => {
      this.report.data = res.body?.items || [];
      this.total = res.body?.total || 0;
    });
  }

  shareReport() {
    this.reportService.shareTodayReport().subscribe(() => {});
  }
}
