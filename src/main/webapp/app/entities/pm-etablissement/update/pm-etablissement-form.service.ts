import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IPmEtablissement, NewPmEtablissement } from '../pm-etablissement.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPmEtablissement for edit and NewPmEtablissementFormGroupInput for create.
 */
type PmEtablissementFormGroupInput = IPmEtablissement | PartialWithRequiredKeyOf<NewPmEtablissement>;

type PmEtablissementFormDefaults = Pick<NewPmEtablissement, 'id' | 'checked' | 'miseEnGestions'>;

type PmEtablissementFormGroupContent = {
  id: FormControl<IPmEtablissement['id'] | NewPmEtablissement['id']>;
  idEtablissementRPG: FormControl<IPmEtablissement['idEtablissementRPG']>;
  codePartenaireDistributeur: FormControl<IPmEtablissement['codePartenaireDistributeur']>;
  numeroSiretSiege: FormControl<IPmEtablissement['numeroSiretSiege']>;
  codeEtat: FormControl<IPmEtablissement['codeEtat']>;
  libelleEtat: FormControl<IPmEtablissement['libelleEtat']>;
  codeCategoriePersonne: FormControl<IPmEtablissement['codeCategoriePersonne']>;
  libelleCategoriePersonne: FormControl<IPmEtablissement['libelleCategoriePersonne']>;
  codeType: FormControl<IPmEtablissement['codeType']>;
  dateCreationJuridique: FormControl<IPmEtablissement['dateCreationJuridique']>;
  dateEtat: FormControl<IPmEtablissement['dateEtat']>;
  dateFermetureJuridique: FormControl<IPmEtablissement['dateFermetureJuridique']>;
  codeAPE: FormControl<IPmEtablissement['codeAPE']>;
  codeIDCC: FormControl<IPmEtablissement['codeIDCC']>;
  codeTrancheEffectif: FormControl<IPmEtablissement['codeTrancheEffectif']>;
  libelleTrancheEffectif: FormControl<IPmEtablissement['libelleTrancheEffectif']>;
  dateCessationActivite: FormControl<IPmEtablissement['dateCessationActivite']>;
  dateEffectifOfficiel: FormControl<IPmEtablissement['dateEffectifOfficiel']>;
  effectifOfficiel: FormControl<IPmEtablissement['effectifOfficiel']>;
  codeMotifCessationActivite: FormControl<IPmEtablissement['codeMotifCessationActivite']>;
  siret: FormControl<IPmEtablissement['siret']>;
  codeTypeEtablissement: FormControl<IPmEtablissement['codeTypeEtablissement']>;
  libelleEnseigne: FormControl<IPmEtablissement['libelleEnseigne']>;
  codeNIC: FormControl<IPmEtablissement['codeNIC']>;
  identifiantAI: FormControl<IPmEtablissement['identifiantAI']>;
  checked: FormControl<IPmEtablissement['checked']>;
  miseEnGestions: FormControl<IPmEtablissement['miseEnGestions']>;
  groupe: FormControl<IPmEtablissement['groupe']>;
  pmEntreprise: FormControl<IPmEtablissement['pmEntreprise']>;
};

export type PmEtablissementFormGroup = FormGroup<PmEtablissementFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PmEtablissementFormService {
  createPmEtablissementFormGroup(pmEtablissement: PmEtablissementFormGroupInput = { id: null }): PmEtablissementFormGroup {
    const pmEtablissementRawValue = {
      ...this.getFormDefaults(),
      ...pmEtablissement,
    };
    return new FormGroup<PmEtablissementFormGroupContent>({
      id: new FormControl(
        { value: pmEtablissementRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idEtablissementRPG: new FormControl(pmEtablissementRawValue.idEtablissementRPG, {
        validators: [Validators.required],
      }),
      codePartenaireDistributeur: new FormControl(pmEtablissementRawValue.codePartenaireDistributeur, {
        validators: [Validators.required],
      }),
      numeroSiretSiege: new FormControl(pmEtablissementRawValue.numeroSiretSiege, {
        validators: [Validators.required],
      }),
      codeEtat: new FormControl(pmEtablissementRawValue.codeEtat, {
        validators: [Validators.required],
      }),
      libelleEtat: new FormControl(pmEtablissementRawValue.libelleEtat),
      codeCategoriePersonne: new FormControl(pmEtablissementRawValue.codeCategoriePersonne, {
        validators: [Validators.required],
      }),
      libelleCategoriePersonne: new FormControl(pmEtablissementRawValue.libelleCategoriePersonne, {
        validators: [Validators.required],
      }),
      codeType: new FormControl(pmEtablissementRawValue.codeType, {
        validators: [Validators.required],
      }),
      dateCreationJuridique: new FormControl(pmEtablissementRawValue.dateCreationJuridique, {
        validators: [Validators.required],
      }),
      dateEtat: new FormControl(pmEtablissementRawValue.dateEtat, {
        validators: [Validators.required],
      }),
      dateFermetureJuridique: new FormControl(pmEtablissementRawValue.dateFermetureJuridique),
      codeAPE: new FormControl(pmEtablissementRawValue.codeAPE, {
        validators: [Validators.required],
      }),
      codeIDCC: new FormControl(pmEtablissementRawValue.codeIDCC),
      codeTrancheEffectif: new FormControl(pmEtablissementRawValue.codeTrancheEffectif),
      libelleTrancheEffectif: new FormControl(pmEtablissementRawValue.libelleTrancheEffectif),
      dateCessationActivite: new FormControl(pmEtablissementRawValue.dateCessationActivite),
      dateEffectifOfficiel: new FormControl(pmEtablissementRawValue.dateEffectifOfficiel),
      effectifOfficiel: new FormControl(pmEtablissementRawValue.effectifOfficiel),
      codeMotifCessationActivite: new FormControl(pmEtablissementRawValue.codeMotifCessationActivite),
      siret: new FormControl(pmEtablissementRawValue.siret, {
        validators: [Validators.required],
      }),
      codeTypeEtablissement: new FormControl(pmEtablissementRawValue.codeTypeEtablissement, {
        validators: [Validators.required],
      }),
      libelleEnseigne: new FormControl(pmEtablissementRawValue.libelleEnseigne, {
        validators: [Validators.required],
      }),
      codeNIC: new FormControl(pmEtablissementRawValue.codeNIC, {
        validators: [Validators.required],
      }),
      identifiantAI: new FormControl(pmEtablissementRawValue.identifiantAI),
      checked: new FormControl(pmEtablissementRawValue.checked),
      miseEnGestions: new FormControl(pmEtablissementRawValue.miseEnGestions ?? []),
      groupe: new FormControl(pmEtablissementRawValue.groupe),
      pmEntreprise: new FormControl(pmEtablissementRawValue.pmEntreprise),
    });
  }

  getPmEtablissement(form: PmEtablissementFormGroup): IPmEtablissement | NewPmEtablissement {
    return form.getRawValue() as IPmEtablissement | NewPmEtablissement;
  }

  resetForm(form: PmEtablissementFormGroup, pmEtablissement: PmEtablissementFormGroupInput): void {
    const pmEtablissementRawValue = { ...this.getFormDefaults(), ...pmEtablissement };
    form.reset(
      {
        ...pmEtablissementRawValue,
        id: { value: pmEtablissementRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PmEtablissementFormDefaults {
    return {
      id: null,
      checked: false,
      miseEnGestions: [],
    };
  }
}
