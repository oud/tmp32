import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IAdresse, NewAdresse } from '../adresse.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAdresse for edit and NewAdresseFormGroupInput for create.
 */
type AdresseFormGroupInput = IAdresse | PartialWithRequiredKeyOf<NewAdresse>;

type AdresseFormDefaults = Pick<NewAdresse, 'id'>;

type AdresseFormGroupContent = {
  id: FormControl<IAdresse['id'] | NewAdresse['id']>;
  codePaysISO: FormControl<IAdresse['codePaysISO']>;
  codePostal: FormControl<IAdresse['codePostal']>;
  dateDerniereModification: FormControl<IAdresse['dateDerniereModification']>;
  codeTypeAdresse: FormControl<IAdresse['codeTypeAdresse']>;
  libelleCommune: FormControl<IAdresse['libelleCommune']>;
  ligne1: FormControl<IAdresse['ligne1']>;
  ligne2: FormControl<IAdresse['ligne2']>;
  ligne3: FormControl<IAdresse['ligne3']>;
  ligne4: FormControl<IAdresse['ligne4']>;
  ligne5: FormControl<IAdresse['ligne5']>;
  ligne6: FormControl<IAdresse['ligne6']>;
  ligne7: FormControl<IAdresse['ligne7']>;
  nombreCourriersPND: FormControl<IAdresse['nombreCourriersPND']>;
  codeUsageAdresse: FormControl<IAdresse['codeUsageAdresse']>;
  pmEtablissement: FormControl<IAdresse['pmEtablissement']>;
};

export type AdresseFormGroup = FormGroup<AdresseFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AdresseFormService {
  createAdresseFormGroup(adresse: AdresseFormGroupInput = { id: null }): AdresseFormGroup {
    const adresseRawValue = {
      ...this.getFormDefaults(),
      ...adresse,
    };
    return new FormGroup<AdresseFormGroupContent>({
      id: new FormControl(
        { value: adresseRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codePaysISO: new FormControl(adresseRawValue.codePaysISO, {
        validators: [Validators.required],
      }),
      codePostal: new FormControl(adresseRawValue.codePostal, {
        validators: [Validators.required],
      }),
      dateDerniereModification: new FormControl(adresseRawValue.dateDerniereModification),
      codeTypeAdresse: new FormControl(adresseRawValue.codeTypeAdresse, {
        validators: [Validators.required],
      }),
      libelleCommune: new FormControl(adresseRawValue.libelleCommune),
      ligne1: new FormControl(adresseRawValue.ligne1),
      ligne2: new FormControl(adresseRawValue.ligne2),
      ligne3: new FormControl(adresseRawValue.ligne3),
      ligne4: new FormControl(adresseRawValue.ligne4),
      ligne5: new FormControl(adresseRawValue.ligne5),
      ligne6: new FormControl(adresseRawValue.ligne6),
      ligne7: new FormControl(adresseRawValue.ligne7),
      nombreCourriersPND: new FormControl(adresseRawValue.nombreCourriersPND),
      codeUsageAdresse: new FormControl(adresseRawValue.codeUsageAdresse, {
        validators: [Validators.required],
      }),
      pmEtablissement: new FormControl(adresseRawValue.pmEtablissement),
    });
  }

  getAdresse(form: AdresseFormGroup): IAdresse | NewAdresse {
    return form.getRawValue() as IAdresse | NewAdresse;
  }

  resetForm(form: AdresseFormGroup, adresse: AdresseFormGroupInput): void {
    const adresseRawValue = { ...this.getFormDefaults(), ...adresse };
    form.reset(
      {
        ...adresseRawValue,
        id: { value: adresseRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AdresseFormDefaults {
    return {
      id: null,
    };
  }
}
