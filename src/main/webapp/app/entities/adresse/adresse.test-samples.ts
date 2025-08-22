import dayjs from 'dayjs/esm';

import { IAdresse, NewAdresse } from './adresse.model';

export const sampleWithRequiredData: IAdresse = {
  id: 11524,
  codePaysISO: 'scientific boohoo',
  codePostal: 'nearly',
  codeTypeAdresse: 'potentially',
  codeUsageAdresse: 'hence than incidentally',
};

export const sampleWithPartialData: IAdresse = {
  id: 522,
  codePaysISO: 'vice heighten than',
  codePostal: 'indeed scratchy',
  dateDerniereModification: dayjs('2025-08-21'),
  codeTypeAdresse: 'angle understanding',
  libelleCommune: 'voluntarily gee duh',
  ligne3: 'arrogantly',
  ligne5: 'attraction under federate',
  nombreCourriersPND: 'ferociously',
  codeUsageAdresse: 'electrify',
};

export const sampleWithFullData: IAdresse = {
  id: 31736,
  codePaysISO: 'beside yum',
  codePostal: 'homely duh',
  dateDerniereModification: dayjs('2025-08-22'),
  codeTypeAdresse: 'elevator',
  libelleCommune: 'limited',
  ligne1: 'ravage toward',
  ligne2: 'nor swanling smarten',
  ligne3: 'godfather for dim',
  ligne4: 'unscramble fuzzy',
  ligne5: 'riser a notwithstanding',
  ligne6: 'with beloved gosh',
  ligne7: 'pneumonia',
  nombreCourriersPND: 'moisten velocity victoriously',
  codeUsageAdresse: 'uh-huh large fledgling',
};

export const sampleWithNewData: NewAdresse = {
  codePaysISO: 'timely',
  codePostal: 'shocked instruction',
  codeTypeAdresse: 'coolly',
  codeUsageAdresse: 'whereas',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
