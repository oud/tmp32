import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IGarantie } from 'app/entities/garantie/garantie.model';
import { GarantieService } from 'app/entities/garantie/service/garantie.service';
import { ITaux } from '../taux.model';
import { TauxService } from '../service/taux.service';
import { TauxFormGroup, TauxFormService } from './taux-form.service';

@Component({
  selector: 'jhi-taux-update',
  templateUrl: './taux-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TauxUpdateComponent implements OnInit {
  isSaving = false;
  taux: ITaux | null = null;

  garantiesSharedCollection: IGarantie[] = [];

  protected tauxService = inject(TauxService);
  protected tauxFormService = inject(TauxFormService);
  protected garantieService = inject(GarantieService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: TauxFormGroup = this.tauxFormService.createTauxFormGroup();

  compareGarantie = (o1: IGarantie | null, o2: IGarantie | null): boolean => this.garantieService.compareGarantie(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taux }) => {
      this.taux = taux;
      if (taux) {
        this.updateForm(taux);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taux = this.tauxFormService.getTaux(this.editForm);
    if (taux.id !== null) {
      this.subscribeToSaveResponse(this.tauxService.update(taux));
    } else {
      this.subscribeToSaveResponse(this.tauxService.create(taux));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaux>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(taux: ITaux): void {
    this.taux = taux;
    this.tauxFormService.resetForm(this.editForm, taux);

    this.garantiesSharedCollection = this.garantieService.addGarantieToCollectionIfMissing<IGarantie>(
      this.garantiesSharedCollection,
      taux.garantie,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.garantieService
      .query()
      .pipe(map((res: HttpResponse<IGarantie[]>) => res.body ?? []))
      .pipe(
        map((garanties: IGarantie[]) => this.garantieService.addGarantieToCollectionIfMissing<IGarantie>(garanties, this.taux?.garantie)),
      )
      .subscribe((garanties: IGarantie[]) => (this.garantiesSharedCollection = garanties));
  }
}
