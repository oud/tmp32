import dayjs from 'dayjs/esm';

import { IPmEntreprise, NewPmEntreprise } from './pm-entreprise.model';

export const sampleWithRequiredData: IPmEntreprise = {
  id: 29267,
  idEntrepriseRPG: 'yippee',
  codePartenaireDistributeur: 'glittering while',
  numeroSiretSiege: 'clearly hmph technician',
  codeEtat: 'duh potable',
  dateCreationJuridique: dayjs('2025-08-21'),
  siren: 'winged mockingly',
  codeFormeJuridique: 'offset for huff',
  raisonSociale: 'unique pish',
  codeAPE: 'inwardly meanwhile',
};

export const sampleWithPartialData: IPmEntreprise = {
  id: 18780,
  idEntrepriseRPG: 'crank',
  codePartenaireDistributeur: 'immaculate aside orientate',
  numeroSiretSiege: 'er imaginative',
  codeEtat: 'pine skean scrutinise',
  codeCategoriePersonne: 'zowie',
  codeType: 'vaguely pfft sympathetically',
  dateCreationJuridique: dayjs('2025-08-22'),
  dateCessationActivite: dayjs('2025-08-22'),
  siren: 'conclude',
  codeFormeJuridique: 'uh-huh justly',
  raisonSociale: 'yum',
  codeAPE: 'yuck convince',
  identifiantAI: 'neat maestro although',
  checked: true,
};

export const sampleWithFullData: IPmEntreprise = {
  id: 9584,
  idEntrepriseRPG: 'chasuble pilot shrill',
  codePartenaireDistributeur: 'bog',
  numeroSiretSiege: 'dally',
  codeEtat: 'satirise ah swiftly',
  libelleEtat: 'yowza fooey brown',
  codeCategoriePersonne: 'evince',
  libelleCategoriePersonne: 'positively',
  codeType: 'measly gosh softly',
  dateCreationJuridique: dayjs('2025-08-22'),
  dateEtat: dayjs('2025-08-21'),
  dateFermetureJuridique: dayjs('2025-08-22'),
  codeTrancheEffectif: 'since',
  libelleTrancheEffectif: 'publication major',
  dateCessationActivite: dayjs('2025-08-21'),
  dateEffectifOfficiel: dayjs('2025-08-21'),
  effectifOfficiel: 'unit',
  codeMotifCessationActivite: 'consequently flood huzzah',
  siren: 'consequently',
  codeFormeJuridique: 'behind',
  raisonSociale: 'silver',
  codeCategorieJuridique: 'longboat gown hunger',
  codeIDCC: 'impact barring since',
  codeAPE: 'at uncork',
  identifiantAI: 'mockingly puff',
  checked: false,
};

export const sampleWithNewData: NewPmEntreprise = {
  idEntrepriseRPG: 'playfully',
  codePartenaireDistributeur: 'whoa whimsical expostulate',
  numeroSiretSiege: 'and unwelcome',
  codeEtat: 'summarise',
  dateCreationJuridique: dayjs('2025-08-22'),
  siren: 'how yearly',
  codeFormeJuridique: 'fatally mid aw',
  raisonSociale: 'disinherit scientific well-to-do',
  codeAPE: 'breakable victoriously regarding',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
