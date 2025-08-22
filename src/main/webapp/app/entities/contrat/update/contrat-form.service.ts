import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IContrat, NewContrat } from '../contrat.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IContrat for edit and NewContratFormGroupInput for create.
 */
type ContratFormGroupInput = IContrat | PartialWithRequiredKeyOf<NewContrat>;

type ContratFormDefaults = Pick<NewContrat, 'id'>;

type ContratFormGroupContent = {
  id: FormControl<IContrat['id'] | NewContrat['id']>;
  numeroContratCollectif: FormControl<IContrat['numeroContratCollectif']>;
  migre: FormControl<IContrat['migre']>;
  codeEntiteRattachement: FormControl<IContrat['codeEntiteRattachement']>;
  codeCentreGestion: FormControl<IContrat['codeCentreGestion']>;
  codeGroupeGestion: FormControl<IContrat['codeGroupeGestion']>;
  codeReseauDistribution: FormControl<IContrat['codeReseauDistribution']>;
  typeContratCollectif: FormControl<IContrat['typeContratCollectif']>;
  etatContrat: FormControl<IContrat['etatContrat']>;
  dateEffetPremiereSouscription: FormControl<IContrat['dateEffetPremiereSouscription']>;
  dateEffetDerniereResiliation: FormControl<IContrat['dateEffetDerniereResiliation']>;
  motifResiliation: FormControl<IContrat['motifResiliation']>;
  codeNatureCouverture: FormControl<IContrat['codeNatureCouverture']>;
  codeOffre: FormControl<IContrat['codeOffre']>;
  numeroVersionOffre: FormControl<IContrat['numeroVersionOffre']>;
  echeancePrincipale: FormControl<IContrat['echeancePrincipale']>;
  codeOrganismePorteurRisque: FormControl<IContrat['codeOrganismePorteurRisque']>;
  indicateurPorteurRisque: FormControl<IContrat['indicateurPorteurRisque']>;
  codeOrganismeProducteurFicheDsn: FormControl<IContrat['codeOrganismeProducteurFicheDsn']>;
  codeOrganismeDelegataireCotisations: FormControl<IContrat['codeOrganismeDelegataireCotisations']>;
  codeOrganismeDelegatairePrestations: FormControl<IContrat['codeOrganismeDelegatairePrestations']>;
  datePremierMoisCotisationAutorise: FormControl<IContrat['datePremierMoisCotisationAutorise']>;
  numeroOperationNiveau0: FormControl<IContrat['numeroOperationNiveau0']>;
  statut: FormControl<IContrat['statut']>;
  pmEntreprise: FormControl<IContrat['pmEntreprise']>;
};

export type ContratFormGroup = FormGroup<ContratFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ContratFormService {
  createContratFormGroup(contrat: ContratFormGroupInput = { id: null }): ContratFormGroup {
    const contratRawValue = {
      ...this.getFormDefaults(),
      ...contrat,
    };
    return new FormGroup<ContratFormGroupContent>({
      id: new FormControl(
        { value: contratRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      numeroContratCollectif: new FormControl(contratRawValue.numeroContratCollectif, {
        validators: [Validators.required],
      }),
      migre: new FormControl(contratRawValue.migre, {
        validators: [Validators.required],
      }),
      codeEntiteRattachement: new FormControl(contratRawValue.codeEntiteRattachement, {
        validators: [Validators.required],
      }),
      codeCentreGestion: new FormControl(contratRawValue.codeCentreGestion, {
        validators: [Validators.required],
      }),
      codeGroupeGestion: new FormControl(contratRawValue.codeGroupeGestion, {
        validators: [Validators.required],
      }),
      codeReseauDistribution: new FormControl(contratRawValue.codeReseauDistribution, {
        validators: [Validators.required],
      }),
      typeContratCollectif: new FormControl(contratRawValue.typeContratCollectif, {
        validators: [Validators.required],
      }),
      etatContrat: new FormControl(contratRawValue.etatContrat, {
        validators: [Validators.required],
      }),
      dateEffetPremiereSouscription: new FormControl(contratRawValue.dateEffetPremiereSouscription, {
        validators: [Validators.required],
      }),
      dateEffetDerniereResiliation: new FormControl(contratRawValue.dateEffetDerniereResiliation),
      motifResiliation: new FormControl(contratRawValue.motifResiliation),
      codeNatureCouverture: new FormControl(contratRawValue.codeNatureCouverture, {
        validators: [Validators.required],
      }),
      codeOffre: new FormControl(contratRawValue.codeOffre, {
        validators: [Validators.required],
      }),
      numeroVersionOffre: new FormControl(contratRawValue.numeroVersionOffre, {
        validators: [Validators.required],
      }),
      echeancePrincipale: new FormControl(contratRawValue.echeancePrincipale, {
        validators: [Validators.required],
      }),
      codeOrganismePorteurRisque: new FormControl(contratRawValue.codeOrganismePorteurRisque, {
        validators: [Validators.required],
      }),
      indicateurPorteurRisque: new FormControl(contratRawValue.indicateurPorteurRisque, {
        validators: [Validators.required],
      }),
      codeOrganismeProducteurFicheDsn: new FormControl(contratRawValue.codeOrganismeProducteurFicheDsn, {
        validators: [Validators.required],
      }),
      codeOrganismeDelegataireCotisations: new FormControl(contratRawValue.codeOrganismeDelegataireCotisations, {
        validators: [Validators.required],
      }),
      codeOrganismeDelegatairePrestations: new FormControl(contratRawValue.codeOrganismeDelegatairePrestations, {
        validators: [Validators.required],
      }),
      datePremierMoisCotisationAutorise: new FormControl(contratRawValue.datePremierMoisCotisationAutorise, {
        validators: [Validators.required],
      }),
      numeroOperationNiveau0: new FormControl(contratRawValue.numeroOperationNiveau0, {
        validators: [Validators.required],
      }),
      statut: new FormControl(contratRawValue.statut, {
        validators: [Validators.required],
      }),
      pmEntreprise: new FormControl(contratRawValue.pmEntreprise),
    });
  }

  getContrat(form: ContratFormGroup): IContrat | NewContrat {
    return form.getRawValue() as IContrat | NewContrat;
  }

  resetForm(form: ContratFormGroup, contrat: ContratFormGroupInput): void {
    const contratRawValue = { ...this.getFormDefaults(), ...contrat };
    form.reset(
      {
        ...contratRawValue,
        id: { value: contratRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ContratFormDefaults {
    return {
      id: null,
    };
  }
}
