import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IGarantie } from '../garantie.model';

@Component({
  selector: 'jhi-garantie-detail',
  templateUrl: './garantie-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class GarantieDetailComponent {
  garantie = input<IGarantie | null>(null);

  previousState(): void {
    window.history.back();
  }
}
