import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IDemandeXRM, NewDemandeXRM } from '../demande-xrm.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDemandeXRM for edit and NewDemandeXRMFormGroupInput for create.
 */
type DemandeXRMFormGroupInput = IDemandeXRM | PartialWithRequiredKeyOf<NewDemandeXRM>;

type DemandeXRMFormDefaults = Pick<NewDemandeXRM, 'id'>;

type DemandeXRMFormGroupContent = {
  id: FormControl<IDemandeXRM['id'] | NewDemandeXRM['id']>;
  dateDemande: FormControl<IDemandeXRM['dateDemande']>;
  dateEnvoiAI: FormControl<IDemandeXRM['dateEnvoiAI']>;
  dateEnvoiIVS: FormControl<IDemandeXRM['dateEnvoiIVS']>;
  aI: FormControl<IDemandeXRM['aI']>;
  iVS: FormControl<IDemandeXRM['iVS']>;
};

export type DemandeXRMFormGroup = FormGroup<DemandeXRMFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DemandeXRMFormService {
  createDemandeXRMFormGroup(demandeXRM: DemandeXRMFormGroupInput = { id: null }): DemandeXRMFormGroup {
    const demandeXRMRawValue = {
      ...this.getFormDefaults(),
      ...demandeXRM,
    };
    return new FormGroup<DemandeXRMFormGroupContent>({
      id: new FormControl(
        { value: demandeXRMRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dateDemande: new FormControl(demandeXRMRawValue.dateDemande),
      dateEnvoiAI: new FormControl(demandeXRMRawValue.dateEnvoiAI),
      dateEnvoiIVS: new FormControl(demandeXRMRawValue.dateEnvoiIVS),
      aI: new FormControl(demandeXRMRawValue.aI),
      iVS: new FormControl(demandeXRMRawValue.iVS),
    });
  }

  getDemandeXRM(form: DemandeXRMFormGroup): IDemandeXRM | NewDemandeXRM {
    return form.getRawValue() as IDemandeXRM | NewDemandeXRM;
  }

  resetForm(form: DemandeXRMFormGroup, demandeXRM: DemandeXRMFormGroupInput): void {
    const demandeXRMRawValue = { ...this.getFormDefaults(), ...demandeXRM };
    form.reset(
      {
        ...demandeXRMRawValue,
        id: { value: demandeXRMRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): DemandeXRMFormDefaults {
    return {
      id: null,
    };
  }
}
