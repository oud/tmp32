import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITaux } from '../taux.model';
import { TauxService } from '../service/taux.service';

@Component({
  templateUrl: './taux-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TauxDeleteDialogComponent {
  taux?: ITaux;

  protected tauxService = inject(TauxService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tauxService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
