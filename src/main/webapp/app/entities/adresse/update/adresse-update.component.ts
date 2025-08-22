import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { PmEtablissementService } from 'app/entities/pm-etablissement/service/pm-etablissement.service';
import { IAdresse } from '../adresse.model';
import { AdresseService } from '../service/adresse.service';
import { AdresseFormGroup, AdresseFormService } from './adresse-form.service';

@Component({
  selector: 'jhi-adresse-update',
  templateUrl: './adresse-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AdresseUpdateComponent implements OnInit {
  isSaving = false;
  adresse: IAdresse | null = null;

  pmEtablissementsSharedCollection: IPmEtablissement[] = [];

  protected adresseService = inject(AdresseService);
  protected adresseFormService = inject(AdresseFormService);
  protected pmEtablissementService = inject(PmEtablissementService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: AdresseFormGroup = this.adresseFormService.createAdresseFormGroup();

  comparePmEtablissement = (o1: IPmEtablissement | null, o2: IPmEtablissement | null): boolean =>
    this.pmEtablissementService.comparePmEtablissement(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adresse }) => {
      this.adresse = adresse;
      if (adresse) {
        this.updateForm(adresse);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adresse = this.adresseFormService.getAdresse(this.editForm);
    if (adresse.id !== null) {
      this.subscribeToSaveResponse(this.adresseService.update(adresse));
    } else {
      this.subscribeToSaveResponse(this.adresseService.create(adresse));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdresse>>): void {
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

  protected updateForm(adresse: IAdresse): void {
    this.adresse = adresse;
    this.adresseFormService.resetForm(this.editForm, adresse);

    this.pmEtablissementsSharedCollection = this.pmEtablissementService.addPmEtablissementToCollectionIfMissing<IPmEtablissement>(
      this.pmEtablissementsSharedCollection,
      adresse.pmEtablissement,
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
            this.adresse?.pmEtablissement,
          ),
        ),
      )
      .subscribe((pmEtablissements: IPmEtablissement[]) => (this.pmEtablissementsSharedCollection = pmEtablissements));
  }
}
