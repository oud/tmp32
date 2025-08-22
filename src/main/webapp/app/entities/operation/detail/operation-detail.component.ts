import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IOperation } from '../operation.model';

@Component({
  selector: 'jhi-operation-detail',
  templateUrl: './operation-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class OperationDetailComponent {
  operation = input<IOperation | null>(null);

  previousState(): void {
    window.history.back();
  }
}
