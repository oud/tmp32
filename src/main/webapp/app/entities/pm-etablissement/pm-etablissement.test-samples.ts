import dayjs from 'dayjs/esm';

import { IPmEtablissement, NewPmEtablissement } from './pm-etablissement.model';

export const sampleWithRequiredData: IPmEtablissement = {
  id: 24935,
  idEtablissementRPG: 'inasmuch',
  codePartenaireDistributeur: 'gee excepting brochure',
  numeroSiretSiege: 'allegation um er',
  codeEtat: 'all hurtful',
  codeCategoriePersonne: 'shrilly gee amidst',
  libelleCategoriePersonne: 'boring beneath cutlet',
  codeType: 'gee aw',
  dateCreationJuridique: dayjs('2025-08-21'),
  dateEtat: dayjs('2025-08-21'),
  codeAPE: 'how traditionalism verbally',
  siret: 'clearly',
  codeTypeEtablissement: 'burly',
  libelleEnseigne: 'yuck',
  codeNIC: 'proceed',
};

export const sampleWithPartialData: IPmEtablissement = {
  id: 23694,
  idEtablissementRPG: 'homeschool mainstream puzzled',
  codePartenaireDistributeur: 'engender rationale vivaciously',
  numeroSiretSiege: 'crocodile',
  codeEtat: 'unless',
  libelleEtat: 'reprove beside hm',
  codeCategoriePersonne: 'respectful',
  libelleCategoriePersonne: 'furiously or provided',
  codeType: 'besides',
  dateCreationJuridique: dayjs('2025-08-22'),
  dateEtat: dayjs('2025-08-22'),
  dateFermetureJuridique: dayjs('2025-08-22'),
  codeAPE: 'pish',
  codeTrancheEffectif: 'gee lazily unnecessarily',
  libelleTrancheEffectif: 'incline fake',
  dateCessationActivite: dayjs('2025-08-21'),
  dateEffectifOfficiel: dayjs('2025-08-21'),
  effectifOfficiel: 'lest misread wherever',
  siret: 'queasily pity',
  codeTypeEtablissement: 'whether',
  libelleEnseigne: 'tune-up',
  codeNIC: 'dusk veto pocket-watch',
  identifiantAI: 'barge afore',
  checked: true,
};

export const sampleWithFullData: IPmEtablissement = {
  id: 22674,
  idEtablissementRPG: 'if yippee',
  codePartenaireDistributeur: 'pry task instead',
  numeroSiretSiege: 'good-natured ironclad',
  codeEtat: 'offset ugh fooey',
  libelleEtat: 'crackle wrongly',
  codeCategoriePersonne: 'for',
  libelleCategoriePersonne: 'tight marksman failing',
  codeType: 'er dress onto',
  dateCreationJuridique: dayjs('2025-08-22'),
  dateEtat: dayjs('2025-08-22'),
  dateFermetureJuridique: dayjs('2025-08-22'),
  codeAPE: 'convoke follower',
  codeIDCC: 'hence yum once',
  codeTrancheEffectif: 'hmph decide',
  libelleTrancheEffectif: 'boohoo',
  dateCessationActivite: dayjs('2025-08-22'),
  dateEffectifOfficiel: dayjs('2025-08-22'),
  effectifOfficiel: 'consign',
  codeMotifCessationActivite: 'terribly',
  siret: 'lazily',
  codeTypeEtablissement: 'throughout blowgun',
  libelleEnseigne: 'before whoa',
  codeNIC: 'pish anenst',
  identifiantAI: 'off shred adjourn',
  checked: true,
};

export const sampleWithNewData: NewPmEtablissement = {
  idEtablissementRPG: 'illiterate as',
  codePartenaireDistributeur: 'apropos',
  numeroSiretSiege: 'as',
  codeEtat: 'so yet',
  codeCategoriePersonne: 'beneath unexpectedly earnest',
  libelleCategoriePersonne: 'handsome carpool kookily',
  codeType: 'augment calmly',
  dateCreationJuridique: dayjs('2025-08-22'),
  dateEtat: dayjs('2025-08-21'),
  codeAPE: 'like',
  siret: 'usefully',
  codeTypeEtablissement: 'excitedly',
  libelleEnseigne: 'eek that',
  codeNIC: 'deselect glisten',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
