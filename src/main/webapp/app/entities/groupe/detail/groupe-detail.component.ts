import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IGroupe } from '../groupe.model';

@Component({
  selector: 'jhi-groupe-detail',
  templateUrl: './groupe-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class GroupeDetailComponent {
  groupe = input<IGroupe | null>(null);

  previousState(): void {
    window.history.back();
  }
}
