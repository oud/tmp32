import dayjs from 'dayjs/esm';

import { IGroupe, NewGroupe } from './groupe.model';

export const sampleWithRequiredData: IGroupe = {
  id: 17940,
  codeGroupeAssures: 'yuck eggplant terrorise',
  codeGroupePopulation: 'overreact bemuse',
  typeGroupeAssures: 'provided',
  dateDebutPeriodeGroupeAssures: dayjs('2025-08-22'),
  libelleGroupeAssuresTypeAutre: 'toothpick hence',
  codeEtatGroupeAssures: 'annex questionably',
};

export const sampleWithPartialData: IGroupe = {
  id: 18816,
  codeGroupeAssures: 'properly bicycle thump',
  codeGroupePopulation: 'around',
  typeGroupeAssures: 'porter upbeat wide-eyed',
  dateDebutPeriodeGroupeAssures: dayjs('2025-08-21'),
  libelleGroupeAssuresTypeAutre: 'promptly slimy deafening',
  codeEtatGroupeAssures: 'notwithstanding',
};

export const sampleWithFullData: IGroupe = {
  id: 9586,
  codeGroupeAssures: 'nab',
  codeGroupePopulation: 'excepting',
  typeGroupeAssures: 'formamide',
  dateDebutPeriodeGroupeAssures: dayjs('2025-08-21'),
  libelleGroupeAssuresTypeAutre: 'sense',
  codeEtatGroupeAssures: 'unfit yippee',
};

export const sampleWithNewData: NewGroupe = {
  codeGroupeAssures: 'coordination harmful guilty',
  codeGroupePopulation: 'wherever where',
  typeGroupeAssures: 'mantua',
  dateDebutPeriodeGroupeAssures: dayjs('2025-08-22'),
  libelleGroupeAssuresTypeAutre: 'engage',
  codeEtatGroupeAssures: 'consequently circa',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
