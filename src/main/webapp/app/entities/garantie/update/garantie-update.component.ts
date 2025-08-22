import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IProduit } from 'app/entities/produit/produit.model';
import { ProduitService } from 'app/entities/produit/service/produit.service';
import { IGarantie } from '../garantie.model';
import { GarantieService } from '../service/garantie.service';
import { GarantieFormGroup, GarantieFormService } from './garantie-form.service';

@Component({
  selector: 'jhi-garantie-update',
  templateUrl: './garantie-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class GarantieUpdateComponent implements OnInit {
  isSaving = false;
  garantie: IGarantie | null = null;

  produitsSharedCollection: IProduit[] = [];

  protected garantieService = inject(GarantieService);
  protected garantieFormService = inject(GarantieFormService);
  protected produitService = inject(ProduitService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: GarantieFormGroup = this.garantieFormService.createGarantieFormGroup();

  compareProduit = (o1: IProduit | null, o2: IProduit | null): boolean => this.produitService.compareProduit(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ garantie }) => {
      this.garantie = garantie;
      if (garantie) {
        this.updateForm(garantie);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const garantie = this.garantieFormService.getGarantie(this.editForm);
    if (garantie.id !== null) {
      this.subscribeToSaveResponse(this.garantieService.update(garantie));
    } else {
      this.subscribeToSaveResponse(this.garantieService.create(garantie));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGarantie>>): void {
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

  protected updateForm(garantie: IGarantie): void {
    this.garantie = garantie;
    this.garantieFormService.resetForm(this.editForm, garantie);

    this.produitsSharedCollection = this.produitService.addProduitToCollectionIfMissing<IProduit>(
      this.produitsSharedCollection,
      garantie.produit,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.produitService
      .query()
      .pipe(map((res: HttpResponse<IProduit[]>) => res.body ?? []))
      .pipe(map((produits: IProduit[]) => this.produitService.addProduitToCollectionIfMissing<IProduit>(produits, this.garantie?.produit)))
      .subscribe((produits: IProduit[]) => (this.produitsSharedCollection = produits));
  }
}
