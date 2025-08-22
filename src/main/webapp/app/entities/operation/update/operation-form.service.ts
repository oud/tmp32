import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOperation, NewOperation } from '../operation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOperation for edit and NewOperationFormGroupInput for create.
 */
type OperationFormGroupInput = IOperation | PartialWithRequiredKeyOf<NewOperation>;

type OperationFormDefaults = Pick<NewOperation, 'id'>;

type OperationFormGroupContent = {
  id: FormControl<IOperation['id'] | NewOperation['id']>;
  numeroOperationNiveau0: FormControl<IOperation['numeroOperationNiveau0']>;
  etatOperation: FormControl<IOperation['etatOperation']>;
  dateEffetOperation: FormControl<IOperation['dateEffetOperation']>;
  dateDemandeOperation: FormControl<IOperation['dateDemandeOperation']>;
  dateCreation: FormControl<IOperation['dateCreation']>;
  codeActeGestion: FormControl<IOperation['codeActeGestion']>;
  numeroOperationAnnulee: FormControl<IOperation['numeroOperationAnnulee']>;
  contrat: FormControl<IOperation['contrat']>;
};

export type OperationFormGroup = FormGroup<OperationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OperationFormService {
  createOperationFormGroup(operation: OperationFormGroupInput = { id: null }): OperationFormGroup {
    const operationRawValue = {
      ...this.getFormDefaults(),
      ...operation,
    };
    return new FormGroup<OperationFormGroupContent>({
      id: new FormControl(
        { value: operationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      numeroOperationNiveau0: new FormControl(operationRawValue.numeroOperationNiveau0, {
        validators: [Validators.required],
      }),
      etatOperation: new FormControl(operationRawValue.etatOperation, {
        validators: [Validators.required],
      }),
      dateEffetOperation: new FormControl(operationRawValue.dateEffetOperation, {
        validators: [Validators.required],
      }),
      dateDemandeOperation: new FormControl(operationRawValue.dateDemandeOperation),
      dateCreation: new FormControl(operationRawValue.dateCreation, {
        validators: [Validators.required],
      }),
      codeActeGestion: new FormControl(operationRawValue.codeActeGestion, {
        validators: [Validators.required],
      }),
      numeroOperationAnnulee: new FormControl(operationRawValue.numeroOperationAnnulee),
      contrat: new FormControl(operationRawValue.contrat),
    });
  }

  getOperation(form: OperationFormGroup): IOperation | NewOperation {
    return form.getRawValue() as IOperation | NewOperation;
  }

  resetForm(form: OperationFormGroup, operation: OperationFormGroupInput): void {
    const operationRawValue = { ...this.getFormDefaults(), ...operation };
    form.reset(
      {
        ...operationRawValue,
        id: { value: operationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OperationFormDefaults {
    return {
      id: null,
    };
  }
}
