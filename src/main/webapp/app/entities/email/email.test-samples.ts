import dayjs from 'dayjs/esm';

import { IEmail, NewEmail } from './email.model';

export const sampleWithRequiredData: IEmail = {
  id: 6371,
  adresseEmail: 'arcade ah arraign',
  codeUsageEmail: 'gaseous duh',
};

export const sampleWithPartialData: IEmail = {
  id: 29685,
  adresseEmail: 'rotating disarm hunt',
  codeStatut: 'amid entwine',
  dateStatut: dayjs('2025-08-22'),
  codeUsageEmail: 'deny characterization merrily',
};

export const sampleWithFullData: IEmail = {
  id: 3643,
  adresseEmail: 'acclaimed coagulate',
  codeStatut: 'cinch',
  dateStatut: dayjs('2025-08-21'),
  codeUsageEmail: 'geez attribute',
};

export const sampleWithNewData: NewEmail = {
  adresseEmail: 'lucky amidst',
  codeUsageEmail: 'needily',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
