import dayjs from 'dayjs/esm';

import { IOperation, NewOperation } from './operation.model';

export const sampleWithRequiredData: IOperation = {
  id: 6430,
  numeroOperationNiveau0: 'boohoo',
  etatOperation: 'unwieldy meager boastfully',
  dateEffetOperation: dayjs('2025-08-22'),
  dateCreation: dayjs('2025-08-21'),
  codeActeGestion: 'bleakly starboard',
};

export const sampleWithPartialData: IOperation = {
  id: 22612,
  numeroOperationNiveau0: 'yieldingly than quinoa',
  etatOperation: 'before',
  dateEffetOperation: dayjs('2025-08-21'),
  dateDemandeOperation: dayjs('2025-08-22'),
  dateCreation: dayjs('2025-08-21'),
  codeActeGestion: 'cleave dampen',
  numeroOperationAnnulee: 'refute',
};

export const sampleWithFullData: IOperation = {
  id: 13289,
  numeroOperationNiveau0: 'knickers unlike',
  etatOperation: 'carpool',
  dateEffetOperation: dayjs('2025-08-22'),
  dateDemandeOperation: dayjs('2025-08-22'),
  dateCreation: dayjs('2025-08-22'),
  codeActeGestion: 'ponder',
  numeroOperationAnnulee: 'about',
};

export const sampleWithNewData: NewOperation = {
  numeroOperationNiveau0: 'list unabashedly minus',
  etatOperation: 'sandy',
  dateEffetOperation: dayjs('2025-08-21'),
  dateCreation: dayjs('2025-08-21'),
  codeActeGestion: 'until',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
