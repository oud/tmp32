import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IContrat } from 'app/entities/contrat/contrat.model';
import { ContratService } from 'app/entities/contrat/service/contrat.service';
import { IOperation } from '../operation.model';
import { OperationService } from '../service/operation.service';
import { OperationFormGroup, OperationFormService } from './operation-form.service';

@Component({
  selector: 'jhi-operation-update',
  templateUrl: './operation-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OperationUpdateComponent implements OnInit {
  isSaving = false;
  operation: IOperation | null = null;

  contratsSharedCollection: IContrat[] = [];

  protected operationService = inject(OperationService);
  protected operationFormService = inject(OperationFormService);
  protected contratService = inject(ContratService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OperationFormGroup = this.operationFormService.createOperationFormGroup();

  compareContrat = (o1: IContrat | null, o2: IContrat | null): boolean => this.contratService.compareContrat(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operation }) => {
      this.operation = operation;
      if (operation) {
        this.updateForm(operation);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operation = this.operationFormService.getOperation(this.editForm);
    if (operation.id !== null) {
      this.subscribeToSaveResponse(this.operationService.update(operation));
    } else {
      this.subscribeToSaveResponse(this.operationService.create(operation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperation>>): void {
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

  protected updateForm(operation: IOperation): void {
    this.operation = operation;
    this.operationFormService.resetForm(this.editForm, operation);

    this.contratsSharedCollection = this.contratService.addContratToCollectionIfMissing<IContrat>(
      this.contratsSharedCollection,
      operation.contrat,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.contratService
      .query()
      .pipe(map((res: HttpResponse<IContrat[]>) => res.body ?? []))
      .pipe(map((contrats: IContrat[]) => this.contratService.addContratToCollectionIfMissing<IContrat>(contrats, this.operation?.contrat)))
      .subscribe((contrats: IContrat[]) => (this.contratsSharedCollection = contrats));
  }
}
