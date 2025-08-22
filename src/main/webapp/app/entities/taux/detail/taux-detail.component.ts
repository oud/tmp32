import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { ITaux } from '../taux.model';

@Component({
  selector: 'jhi-taux-detail',
  templateUrl: './taux-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class TauxDetailComponent {
  taux = input<ITaux | null>(null);

  previousState(): void {
    window.history.back();
  }
}
