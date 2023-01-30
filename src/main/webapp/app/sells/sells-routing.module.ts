import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.ShopModule),
        data: {
          pageTitle: 'shop.title',
        },
      },
      {
        path: 'report/billing',
        loadChildren: () => import('./report/report.module').then(m => m.ReportModule),
        data: {
          pageTitle: 'shop.title',
        },
      },
    ]),
  ],
})
export class SellsRoutingModule {}
