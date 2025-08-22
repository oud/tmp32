import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IProduit, NewProduit } from '../produit.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProduit for edit and NewProduitFormGroupInput for create.
 */
type ProduitFormGroupInput = IProduit | PartialWithRequiredKeyOf<NewProduit>;

type ProduitFormDefaults = Pick<NewProduit, 'id'>;

type ProduitFormGroupContent = {
  id: FormControl<IProduit['id'] | NewProduit['id']>;
  codeProduit: FormControl<IProduit['codeProduit']>;
  dateAdhesionProduit: FormControl<IProduit['dateAdhesionProduit']>;
  dateRadiationProduit: FormControl<IProduit['dateRadiationProduit']>;
  codeFormule: FormControl<IProduit['codeFormule']>;
  codeFamilleRisqueFormule: FormControl<IProduit['codeFamilleRisqueFormule']>;
  titreFormule: FormControl<IProduit['titreFormule']>;
  typeFormule: FormControl<IProduit['typeFormule']>;
  codeEtat: FormControl<IProduit['codeEtat']>;
  groupe: FormControl<IProduit['groupe']>;
};

export type ProduitFormGroup = FormGroup<ProduitFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProduitFormService {
  createProduitFormGroup(produit: ProduitFormGroupInput = { id: null }): ProduitFormGroup {
    const produitRawValue = {
      ...this.getFormDefaults(),
      ...produit,
    };
    return new FormGroup<ProduitFormGroupContent>({
      id: new FormControl(
        { value: produitRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codeProduit: new FormControl(produitRawValue.codeProduit, {
        validators: [Validators.required],
      }),
      dateAdhesionProduit: new FormControl(produitRawValue.dateAdhesionProduit, {
        validators: [Validators.required],
      }),
      dateRadiationProduit: new FormControl(produitRawValue.dateRadiationProduit),
      codeFormule: new FormControl(produitRawValue.codeFormule, {
        validators: [Validators.required],
      }),
      codeFamilleRisqueFormule: new FormControl(produitRawValue.codeFamilleRisqueFormule, {
        validators: [Validators.required],
      }),
      titreFormule: new FormControl(produitRawValue.titreFormule, {
        validators: [Validators.required],
      }),
      typeFormule: new FormControl(produitRawValue.typeFormule, {
        validators: [Validators.required],
      }),
      codeEtat: new FormControl(produitRawValue.codeEtat, {
        validators: [Validators.required],
      }),
      groupe: new FormControl(produitRawValue.groupe),
    });
  }

  getProduit(form: ProduitFormGroup): IProduit | NewProduit {
    return form.getRawValue() as IProduit | NewProduit;
  }

  resetForm(form: ProduitFormGroup, produit: ProduitFormGroupInput): void {
    const produitRawValue = { ...this.getFormDefaults(), ...produit };
    form.reset(
      {
        ...produitRawValue,
        id: { value: produitRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ProduitFormDefaults {
    return {
      id: null,
    };
  }
}
