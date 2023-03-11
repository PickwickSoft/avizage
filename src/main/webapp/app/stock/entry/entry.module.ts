import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { EntryComponent } from './entry.component';
import { entryRoute } from './entry.route';
import { CategoryUpdateComponent } from './category/update/category-update.component';

@NgModule({
  declarations: [EntryComponent, CategoryUpdateComponent],
  imports: [SharedModule, RouterModule.forChild(entryRoute)],
})
export class EntryModule {}
