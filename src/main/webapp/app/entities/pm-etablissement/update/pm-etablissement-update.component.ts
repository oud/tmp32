import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IGroupe } from 'app/entities/groupe/groupe.model';
import { GroupeService } from 'app/entities/groupe/service/groupe.service';
import { IPmEntreprise } from 'app/entities/pm-entreprise/pm-entreprise.model';
import { PmEntrepriseService } from 'app/entities/pm-entreprise/service/pm-entreprise.service';
import { PmEtablissementService } from '../service/pm-etablissement.service';
import { IPmEtablissement } from '../pm-etablissement.model';
import { PmEtablissementFormGroup, PmEtablissementFormService } from './pm-etablissement-form.service';

@Component({
  selector: 'jhi-pm-etablissement-update',
  templateUrl: './pm-etablissement-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PmEtablissementUpdateComponent implements OnInit {
  isSaving = false;
  pmEtablissement: IPmEtablissement | null = null;

  groupesSharedCollection: IGroupe[] = [];
  pmEntreprisesSharedCollection: IPmEntreprise[] = [];

  protected pmEtablissementService = inject(PmEtablissementService);
  protected pmEtablissementFormService = inject(PmEtablissementFormService);
  protected groupeService = inject(GroupeService);
  protected pmEntrepriseService = inject(PmEntrepriseService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PmEtablissementFormGroup = this.pmEtablissementFormService.createPmEtablissementFormGroup();

  compareGroupe = (o1: IGroupe | null, o2: IGroupe | null): boolean => this.groupeService.compareGroupe(o1, o2);

  comparePmEntreprise = (o1: IPmEntreprise | null, o2: IPmEntreprise | null): boolean =>
    this.pmEntrepriseService.comparePmEntreprise(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pmEtablissement }) => {
      this.pmEtablissement = pmEtablissement;
      if (pmEtablissement) {
        this.updateForm(pmEtablissement);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pmEtablissement = this.pmEtablissementFormService.getPmEtablissement(this.editForm);
    if (pmEtablissement.id !== null) {
      this.subscribeToSaveResponse(this.pmEtablissementService.update(pmEtablissement));
    } else {
      this.subscribeToSaveResponse(this.pmEtablissementService.create(pmEtablissement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPmEtablissement>>): void {
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

  protected updateForm(pmEtablissement: IPmEtablissement): void {
    this.pmEtablissement = pmEtablissement;
    this.pmEtablissementFormService.resetForm(this.editForm, pmEtablissement);

    this.groupesSharedCollection = this.groupeService.addGroupeToCollectionIfMissing<IGroupe>(
      this.groupesSharedCollection,
      pmEtablissement.groupe,
    );
    this.pmEntreprisesSharedCollection = this.pmEntrepriseService.addPmEntrepriseToCollectionIfMissing<IPmEntreprise>(
      this.pmEntreprisesSharedCollection,
      pmEtablissement.pmEntreprise,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.groupeService
      .query()
      .pipe(map((res: HttpResponse<IGroupe[]>) => res.body ?? []))
      .pipe(map((groupes: IGroupe[]) => this.groupeService.addGroupeToCollectionIfMissing<IGroupe>(groupes, this.pmEtablissement?.groupe)))
      .subscribe((groupes: IGroupe[]) => (this.groupesSharedCollection = groupes));

    this.pmEntrepriseService
      .query()
      .pipe(map((res: HttpResponse<IPmEntreprise[]>) => res.body ?? []))
      .pipe(
        map((pmEntreprises: IPmEntreprise[]) =>
          this.pmEntrepriseService.addPmEntrepriseToCollectionIfMissing<IPmEntreprise>(pmEntreprises, this.pmEtablissement?.pmEntreprise),
        ),
      )
      .subscribe((pmEntreprises: IPmEntreprise[]) => (this.pmEntreprisesSharedCollection = pmEntreprises));
  }
}
