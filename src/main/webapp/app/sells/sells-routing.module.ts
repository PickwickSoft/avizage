import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.ShopModule),
        data: {
          pageTitle: 'shop.home.title',
        },
      },
    ]),
  ],
})
export class SellsRoutingModule {}
