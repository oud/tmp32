import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { PmEtablissementService } from 'app/entities/pm-etablissement/service/pm-etablissement.service';
import { IDemandeXRM } from 'app/entities/demande-xrm/demande-xrm.model';
import { DemandeXRMService } from 'app/entities/demande-xrm/service/demande-xrm.service';
import { MiseEnGestionService } from '../service/mise-en-gestion.service';
import { IMiseEnGestion } from '../mise-en-gestion.model';
import { MiseEnGestionFormGroup, MiseEnGestionFormService } from './mise-en-gestion-form.service';

@Component({
  selector: 'jhi-mise-en-gestion-update',
  templateUrl: './mise-en-gestion-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MiseEnGestionUpdateComponent implements OnInit {
  isSaving = false;
  miseEnGestion: IMiseEnGestion | null = null;

  pmEtablissementsSharedCollection: IPmEtablissement[] = [];
  demandeXRMSSharedCollection: IDemandeXRM[] = [];

  protected miseEnGestionService = inject(MiseEnGestionService);
  protected miseEnGestionFormService = inject(MiseEnGestionFormService);
  protected pmEtablissementService = inject(PmEtablissementService);
  protected demandeXRMService = inject(DemandeXRMService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: MiseEnGestionFormGroup = this.miseEnGestionFormService.createMiseEnGestionFormGroup();

  comparePmEtablissement = (o1: IPmEtablissement | null, o2: IPmEtablissement | null): boolean =>
    this.pmEtablissementService.comparePmEtablissement(o1, o2);

  compareDemandeXRM = (o1: IDemandeXRM | null, o2: IDemandeXRM | null): boolean => this.demandeXRMService.compareDemandeXRM(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ miseEnGestion }) => {
      this.miseEnGestion = miseEnGestion;
      if (miseEnGestion) {
        this.updateForm(miseEnGestion);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const miseEnGestion = this.miseEnGestionFormService.getMiseEnGestion(this.editForm);
    if (miseEnGestion.id !== null) {
      this.subscribeToSaveResponse(this.miseEnGestionService.update(miseEnGestion));
    } else {
      this.subscribeToSaveResponse(this.miseEnGestionService.create(miseEnGestion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMiseEnGestion>>): void {
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

  protected updateForm(miseEnGestion: IMiseEnGestion): void {
    this.miseEnGestion = miseEnGestion;
    this.miseEnGestionFormService.resetForm(this.editForm, miseEnGestion);

    this.pmEtablissementsSharedCollection = this.pmEtablissementService.addPmEtablissementToCollectionIfMissing<IPmEtablissement>(
      this.pmEtablissementsSharedCollection,
      miseEnGestion.pmEtablissement,
    );
    this.demandeXRMSSharedCollection = this.demandeXRMService.addDemandeXRMToCollectionIfMissing<IDemandeXRM>(
      this.demandeXRMSSharedCollection,
      miseEnGestion.demandeXRM,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.pmEtablissementService
      .query()
      .pipe(map((res: HttpResponse<IPmEtablissement[]>) => res.body ?? []))
      .pipe(
        map((pmEtablissements: IPmEtablissement[]) =>
          this.pmEtablissementService.addPmEtablissementToCollectionIfMissing<IPmEtablissement>(
            pmEtablissements,
            this.miseEnGestion?.pmEtablissement,
          ),
        ),
      )
      .subscribe((pmEtablissements: IPmEtablissement[]) => (this.pmEtablissementsSharedCollection = pmEtablissements));

    this.demandeXRMService
      .query()
      .pipe(map((res: HttpResponse<IDemandeXRM[]>) => res.body ?? []))
      .pipe(
        map((demandeXRMS: IDemandeXRM[]) =>
          this.demandeXRMService.addDemandeXRMToCollectionIfMissing<IDemandeXRM>(demandeXRMS, this.miseEnGestion?.demandeXRM),
        ),
      )
      .subscribe((demandeXRMS: IDemandeXRM[]) => (this.demandeXRMSSharedCollection = demandeXRMS));
  }
}
