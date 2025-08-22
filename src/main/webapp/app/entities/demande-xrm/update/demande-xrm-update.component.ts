import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IMiseEnGestion } from 'app/entities/mise-en-gestion/mise-en-gestion.model';
import { MiseEnGestionService } from 'app/entities/mise-en-gestion/service/mise-en-gestion.service';
import { Status } from 'app/entities/enumerations/status.model';
import { DemandeXRMService } from '../service/demande-xrm.service';
import { IDemandeXRM } from '../demande-xrm.model';
import { DemandeXRMFormGroup, DemandeXRMFormService } from './demande-xrm-form.service';

@Component({
  selector: 'jhi-demande-xrm-update',
  templateUrl: './demande-xrm-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DemandeXRMUpdateComponent implements OnInit {
  isSaving = false;
  demandeXRM: IDemandeXRM | null = null;
  statusValues = Object.keys(Status);

  miseEnGestionsSharedCollection: IMiseEnGestion[] = [];

  protected demandeXRMService = inject(DemandeXRMService);
  protected demandeXRMFormService = inject(DemandeXRMFormService);
  protected miseEnGestionService = inject(MiseEnGestionService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: DemandeXRMFormGroup = this.demandeXRMFormService.createDemandeXRMFormGroup();

  compareMiseEnGestion = (o1: IMiseEnGestion | null, o2: IMiseEnGestion | null): boolean =>
    this.miseEnGestionService.compareMiseEnGestion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandeXRM }) => {
      this.demandeXRM = demandeXRM;
      if (demandeXRM) {
        this.updateForm(demandeXRM);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demandeXRM = this.demandeXRMFormService.getDemandeXRM(this.editForm);
    if (demandeXRM.id !== null) {
      this.subscribeToSaveResponse(this.demandeXRMService.update(demandeXRM));
    } else {
      this.subscribeToSaveResponse(this.demandeXRMService.create(demandeXRM));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeXRM>>): void {
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

  protected updateForm(demandeXRM: IDemandeXRM): void {
    this.demandeXRM = demandeXRM;
    this.demandeXRMFormService.resetForm(this.editForm, demandeXRM);

    this.miseEnGestionsSharedCollection = this.miseEnGestionService.addMiseEnGestionToCollectionIfMissing<IMiseEnGestion>(
      this.miseEnGestionsSharedCollection,
      ...(demandeXRM.miseEnGestions ?? []),
    );
  }

  protected loadRelationshipsOptions(): void {
    this.miseEnGestionService
      .query()
      .pipe(map((res: HttpResponse<IMiseEnGestion[]>) => res.body ?? []))
      .pipe(
        map((miseEnGestions: IMiseEnGestion[]) =>
          this.miseEnGestionService.addMiseEnGestionToCollectionIfMissing<IMiseEnGestion>(
            miseEnGestions,
            ...(this.demandeXRM?.miseEnGestions ?? []),
          ),
        ),
      )
      .subscribe((miseEnGestions: IMiseEnGestion[]) => (this.miseEnGestionsSharedCollection = miseEnGestions));
  }
}
