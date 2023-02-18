import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'entry',
        loadChildren: () => import('./entry/entry.module').then(m => m.EntryModule),
        data: {
          pageTitle: 'stock.title',
        },
      },
    ]),
  ],
})
export class StockRoutingModule {}
