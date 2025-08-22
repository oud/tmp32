import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPmEntreprise } from 'app/entities/pm-entreprise/pm-entreprise.model';
import { PmEntrepriseService } from 'app/entities/pm-entreprise/service/pm-entreprise.service';
import { IContrat } from '../contrat.model';
import { ContratService } from '../service/contrat.service';
import { ContratFormGroup, ContratFormService } from './contrat-form.service';

@Component({
  selector: 'jhi-contrat-update',
  templateUrl: './contrat-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ContratUpdateComponent implements OnInit {
  isSaving = false;
  contrat: IContrat | null = null;

  pmEntreprisesCollection: IPmEntreprise[] = [];

  protected contratService = inject(ContratService);
  protected contratFormService = inject(ContratFormService);
  protected pmEntrepriseService = inject(PmEntrepriseService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ContratFormGroup = this.contratFormService.createContratFormGroup();

  comparePmEntreprise = (o1: IPmEntreprise | null, o2: IPmEntreprise | null): boolean =>
    this.pmEntrepriseService.comparePmEntreprise(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contrat }) => {
      this.contrat = contrat;
      if (contrat) {
        this.updateForm(contrat);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contrat = this.contratFormService.getContrat(this.editForm);
    if (contrat.id !== null) {
      this.subscribeToSaveResponse(this.contratService.update(contrat));
    } else {
      this.subscribeToSaveResponse(this.contratService.create(contrat));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContrat>>): void {
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

  protected updateForm(contrat: IContrat): void {
    this.contrat = contrat;
    this.contratFormService.resetForm(this.editForm, contrat);

    this.pmEntreprisesCollection = this.pmEntrepriseService.addPmEntrepriseToCollectionIfMissing<IPmEntreprise>(
      this.pmEntreprisesCollection,
      contrat.pmEntreprise,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.pmEntrepriseService
      .query({ filter: 'contrat-is-null' })
      .pipe(map((res: HttpResponse<IPmEntreprise[]>) => res.body ?? []))
      .pipe(
        map((pmEntreprises: IPmEntreprise[]) =>
          this.pmEntrepriseService.addPmEntrepriseToCollectionIfMissing<IPmEntreprise>(pmEntreprises, this.contrat?.pmEntreprise),
        ),
      )
      .subscribe((pmEntreprises: IPmEntreprise[]) => (this.pmEntreprisesCollection = pmEntreprises));
  }
}
