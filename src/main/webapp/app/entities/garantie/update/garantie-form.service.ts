import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IGarantie, NewGarantie } from '../garantie.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IGarantie for edit and NewGarantieFormGroupInput for create.
 */
type GarantieFormGroupInput = IGarantie | PartialWithRequiredKeyOf<NewGarantie>;

type GarantieFormDefaults = Pick<NewGarantie, 'id'>;

type GarantieFormGroupContent = {
  id: FormControl<IGarantie['id'] | NewGarantie['id']>;
  codeGarantieTechnique: FormControl<IGarantie['codeGarantieTechnique']>;
  codeEtatGarantie: FormControl<IGarantie['codeEtatGarantie']>;
  dateAdhesionGarantie: FormControl<IGarantie['dateAdhesionGarantie']>;
  dateRadiationGarantie: FormControl<IGarantie['dateRadiationGarantie']>;
  codeAssureur: FormControl<IGarantie['codeAssureur']>;
  codeFormule: FormControl<IGarantie['codeFormule']>;
  codePack: FormControl<IGarantie['codePack']>;
  typePack: FormControl<IGarantie['typePack']>;
  titrePack: FormControl<IGarantie['titrePack']>;
  codeSousPack: FormControl<IGarantie['codeSousPack']>;
  typeSousPack: FormControl<IGarantie['typeSousPack']>;
  titreSousPack: FormControl<IGarantie['titreSousPack']>;
  codeTypePrestations: FormControl<IGarantie['codeTypePrestations']>;
  produit: FormControl<IGarantie['produit']>;
};

export type GarantieFormGroup = FormGroup<GarantieFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class GarantieFormService {
  createGarantieFormGroup(garantie: GarantieFormGroupInput = { id: null }): GarantieFormGroup {
    const garantieRawValue = {
      ...this.getFormDefaults(),
      ...garantie,
    };
    return new FormGroup<GarantieFormGroupContent>({
      id: new FormControl(
        { value: garantieRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codeGarantieTechnique: new FormControl(garantieRawValue.codeGarantieTechnique, {
        validators: [Validators.required],
      }),
      codeEtatGarantie: new FormControl(garantieRawValue.codeEtatGarantie, {
        validators: [Validators.required],
      }),
      dateAdhesionGarantie: new FormControl(garantieRawValue.dateAdhesionGarantie, {
        validators: [Validators.required],
      }),
      dateRadiationGarantie: new FormControl(garantieRawValue.dateRadiationGarantie, {
        validators: [Validators.required],
      }),
      codeAssureur: new FormControl(garantieRawValue.codeAssureur, {
        validators: [Validators.required],
      }),
      codeFormule: new FormControl(garantieRawValue.codeFormule, {
        validators: [Validators.required],
      }),
      codePack: new FormControl(garantieRawValue.codePack, {
        validators: [Validators.required],
      }),
      typePack: new FormControl(garantieRawValue.typePack, {
        validators: [Validators.required],
      }),
      titrePack: new FormControl(garantieRawValue.titrePack, {
        validators: [Validators.required],
      }),
      codeSousPack: new FormControl(garantieRawValue.codeSousPack, {
        validators: [Validators.required],
      }),
      typeSousPack: new FormControl(garantieRawValue.typeSousPack, {
        validators: [Validators.required],
      }),
      titreSousPack: new FormControl(garantieRawValue.titreSousPack, {
        validators: [Validators.required],
      }),
      codeTypePrestations: new FormControl(garantieRawValue.codeTypePrestations, {
        validators: [Validators.required],
      }),
      produit: new FormControl(garantieRawValue.produit),
    });
  }

  getGarantie(form: GarantieFormGroup): IGarantie | NewGarantie {
    return form.getRawValue() as IGarantie | NewGarantie;
  }

  resetForm(form: GarantieFormGroup, garantie: GarantieFormGroupInput): void {
    const garantieRawValue = { ...this.getFormDefaults(), ...garantie };
    form.reset(
      {
        ...garantieRawValue,
        id: { value: garantieRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): GarantieFormDefaults {
    return {
      id: null,
    };
  }
}
