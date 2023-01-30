import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { reportRoute } from './report.route';
import { BillingComponent } from './billing/billing.component';

@NgModule({
  declarations: [BillingComponent],
  imports: [SharedModule, RouterModule.forChild(reportRoute)],
})
export class ReportModule {}
