import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IPmEtablissement } from '../pm-etablissement.model';

@Component({
  selector: 'jhi-pm-etablissement-detail',
  templateUrl: './pm-etablissement-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class PmEtablissementDetailComponent {
  pmEtablissement = input<IPmEtablissement | null>(null);

  previousState(): void {
    window.history.back();
  }
}
