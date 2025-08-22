import dayjs from 'dayjs/esm';

import { IMiseEnGestion, NewMiseEnGestion } from './mise-en-gestion.model';

export const sampleWithRequiredData: IMiseEnGestion = {
  id: 16315,
  codeTypeMiseEnGestion: 'cutover shrilly',
  codeOffre: 'or meanwhile whoever',
  dateEffet: dayjs('2025-08-22'),
};

export const sampleWithPartialData: IMiseEnGestion = {
  id: 2422,
  codeTypeMiseEnGestion: 'annual circulate',
  codeOffre: 'apprehensive jubilantly',
  dateEffet: dayjs('2025-08-21'),
};

export const sampleWithFullData: IMiseEnGestion = {
  id: 13488,
  codeTypeMiseEnGestion: 'eek impractical crystallize',
  codeOffre: 'pry',
  dateEffet: dayjs('2025-08-22'),
};

export const sampleWithNewData: NewMiseEnGestion = {
  codeTypeMiseEnGestion: 'why',
  codeOffre: 'so controvert',
  dateEffet: dayjs('2025-08-21'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
