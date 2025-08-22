import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { FormatMediumDatePipe } from 'app/shared/date';
import { IProduit } from '../produit.model';

@Component({
  selector: 'jhi-produit-detail',
  templateUrl: './produit-detail.component.html',
  imports: [SharedModule, RouterModule, FormatMediumDatePipe],
})
export class ProduitDetailComponent {
  produit = input<IProduit | null>(null);

  previousState(): void {
    window.history.back();
  }
}
