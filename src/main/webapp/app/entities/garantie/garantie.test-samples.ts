import dayjs from 'dayjs/esm';

import { IGarantie, NewGarantie } from './garantie.model';

export const sampleWithRequiredData: IGarantie = {
  id: 26408,
  codeGarantieTechnique: 'gracefully',
  codeEtatGarantie: 'assured rowdy jaunty',
  dateAdhesionGarantie: dayjs('2025-08-21'),
  dateRadiationGarantie: dayjs('2025-08-21'),
  codeAssureur: 'the',
  codeFormule: 'outsource',
  codePack: 'unabashedly verify writhing',
  typePack: 'liquid',
  titrePack: 'unbearably brr ew',
  codeSousPack: 'if',
  typeSousPack: 'inside anenst',
  titreSousPack: 'boo sentimental',
  codeTypePrestations: 'per except bah',
};

export const sampleWithPartialData: IGarantie = {
  id: 17902,
  codeGarantieTechnique: 'although ouch guard',
  codeEtatGarantie: 'video partially obediently',
  dateAdhesionGarantie: dayjs('2025-08-22'),
  dateRadiationGarantie: dayjs('2025-08-21'),
  codeAssureur: 'yowza enormously negotiation',
  codeFormule: 'junior',
  codePack: 'mad tame',
  typePack: 'or cop-out',
  titrePack: 'purse',
  codeSousPack: 'needily singing',
  typeSousPack: 'disarm',
  titreSousPack: 'bah',
  codeTypePrestations: 'reapply',
};

export const sampleWithFullData: IGarantie = {
  id: 6986,
  codeGarantieTechnique: 'alongside westernize rebuff',
  codeEtatGarantie: 'militate until actually',
  dateAdhesionGarantie: dayjs('2025-08-21'),
  dateRadiationGarantie: dayjs('2025-08-21'),
  codeAssureur: 'acidic sensitize cautiously',
  codeFormule: 'ah pro',
  codePack: 'after',
  typePack: 'regular lest',
  titrePack: 'complete as',
  codeSousPack: 'midwife upbeat lest',
  typeSousPack: 'given',
  titreSousPack: 'switchboard',
  codeTypePrestations: 'oh instead accidentally',
};

export const sampleWithNewData: NewGarantie = {
  codeGarantieTechnique: 'editor inborn',
  codeEtatGarantie: 'scorn why replacement',
  dateAdhesionGarantie: dayjs('2025-08-22'),
  dateRadiationGarantie: dayjs('2025-08-21'),
  codeAssureur: 'bolster till steak',
  codeFormule: 'selfish trusting grandson',
  codePack: 'nun over skateboard',
  typePack: 'though yet',
  titrePack: 'improbable blah meander',
  codeSousPack: 'barring lotion lively',
  typeSousPack: 'whenever',
  titreSousPack: 'dwell unless',
  codeTypePrestations: 'yowza gently case',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
