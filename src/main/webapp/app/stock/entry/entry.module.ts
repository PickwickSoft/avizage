import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { EntryComponent } from './entry.component';
import { entryRoute } from './entry.route';

@NgModule({
  declarations: [EntryComponent],
  imports: [SharedModule, RouterModule.forChild(entryRoute)],
})
export class EntryModule {}
