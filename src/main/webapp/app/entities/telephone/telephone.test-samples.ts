import dayjs from 'dayjs/esm';

import { ITelephone, NewTelephone } from './telephone.model';

export const sampleWithRequiredData: ITelephone = {
  id: 9770,
  codeTypeTelephone: 'whoa huzzah mask',
  numeroTelephone: 'inasmuch phooey',
};

export const sampleWithPartialData: ITelephone = {
  id: 31642,
  codeTypeTelephone: 'and pfft',
  codePaysISO: 'practical aha fluff',
  dateDerniereModification: dayjs('2025-08-22'),
  numeroTelephone: 'strange',
  codeStatut: 'drat uh-huh',
  codeUsageTelephone: 'boohoo over',
};

export const sampleWithFullData: ITelephone = {
  id: 15888,
  codeTypeTelephone: 'trusty anti',
  codePaysISO: 'magnificent whose',
  dateDerniereModification: dayjs('2025-08-21'),
  codeIndicatifPays: 'pharmacopoeia bah',
  numeroTelephone: 'which sedately',
  codeStatut: 'merrily fearless',
  dateStatut: 'pfft',
  codeUsageTelephone: 'suspiciously',
};

export const sampleWithNewData: NewTelephone = {
  codeTypeTelephone: 'willfully because',
  numeroTelephone: 'meal',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
