import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IGroupe, NewGroupe } from '../groupe.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IGroupe for edit and NewGroupeFormGroupInput for create.
 */
type GroupeFormGroupInput = IGroupe | PartialWithRequiredKeyOf<NewGroupe>;

type GroupeFormDefaults = Pick<NewGroupe, 'id'>;

type GroupeFormGroupContent = {
  id: FormControl<IGroupe['id'] | NewGroupe['id']>;
  codeGroupeAssures: FormControl<IGroupe['codeGroupeAssures']>;
  codeGroupePopulation: FormControl<IGroupe['codeGroupePopulation']>;
  typeGroupeAssures: FormControl<IGroupe['typeGroupeAssures']>;
  dateDebutPeriodeGroupeAssures: FormControl<IGroupe['dateDebutPeriodeGroupeAssures']>;
  libelleGroupeAssuresTypeAutre: FormControl<IGroupe['libelleGroupeAssuresTypeAutre']>;
  codeEtatGroupeAssures: FormControl<IGroupe['codeEtatGroupeAssures']>;
  contrat: FormControl<IGroupe['contrat']>;
};

export type GroupeFormGroup = FormGroup<GroupeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class GroupeFormService {
  createGroupeFormGroup(groupe: GroupeFormGroupInput = { id: null }): GroupeFormGroup {
    const groupeRawValue = {
      ...this.getFormDefaults(),
      ...groupe,
    };
    return new FormGroup<GroupeFormGroupContent>({
      id: new FormControl(
        { value: groupeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codeGroupeAssures: new FormControl(groupeRawValue.codeGroupeAssures, {
        validators: [Validators.required],
      }),
      codeGroupePopulation: new FormControl(groupeRawValue.codeGroupePopulation, {
        validators: [Validators.required],
      }),
      typeGroupeAssures: new FormControl(groupeRawValue.typeGroupeAssures, {
        validators: [Validators.required],
      }),
      dateDebutPeriodeGroupeAssures: new FormControl(groupeRawValue.dateDebutPeriodeGroupeAssures, {
        validators: [Validators.required],
      }),
      libelleGroupeAssuresTypeAutre: new FormControl(groupeRawValue.libelleGroupeAssuresTypeAutre, {
        validators: [Validators.required],
      }),
      codeEtatGroupeAssures: new FormControl(groupeRawValue.codeEtatGroupeAssures, {
        validators: [Validators.required],
      }),
      contrat: new FormControl(groupeRawValue.contrat),
    });
  }

  getGroupe(form: GroupeFormGroup): IGroupe | NewGroupe {
    return form.getRawValue() as IGroupe | NewGroupe;
  }

  resetForm(form: GroupeFormGroup, groupe: GroupeFormGroupInput): void {
    const groupeRawValue = { ...this.getFormDefaults(), ...groupe };
    form.reset(
      {
        ...groupeRawValue,
        id: { value: groupeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): GroupeFormDefaults {
    return {
      id: null,
    };
  }
}
