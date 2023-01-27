import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { ShopComponent } from './kiosk/shop.component';
import { shopRoute } from './shop.route';
import { PriceDialogComponent } from './price-dialog/price-dialog.component';

@NgModule({
  declarations: [ShopComponent, PriceDialogComponent],
  imports: [SharedModule, RouterModule.forChild(shopRoute)],
})
export class ShopModule {}
