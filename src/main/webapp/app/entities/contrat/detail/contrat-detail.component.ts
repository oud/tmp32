import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IContrat } from '../contrat.model';

@Component({
  selector: 'jhi-contrat-detail',
  templateUrl: './contrat-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class ContratDetailComponent {
  contrat = input<IContrat | null>(null);

  previousState(): void {
    window.history.back();
  }
}
