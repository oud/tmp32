import dayjs from 'dayjs/esm';

import { IProduit, NewProduit } from './produit.model';

export const sampleWithRequiredData: IProduit = {
  id: 10819,
  codeProduit: 'cap angelic huzzah',
  dateAdhesionProduit: dayjs('2025-08-21'),
  codeFormule: 'circa impanel netsuke',
  codeFamilleRisqueFormule: 'carp analogy premise',
  titreFormule: 'flame but zesty',
  typeFormule: 'however moor ick',
  codeEtat: 'cuddly down',
};

export const sampleWithPartialData: IProduit = {
  id: 15214,
  codeProduit: 'slake',
  dateAdhesionProduit: dayjs('2025-08-22'),
  dateRadiationProduit: dayjs('2025-08-22'),
  codeFormule: 'whoever',
  codeFamilleRisqueFormule: 'unless equal',
  titreFormule: 'ew discourse',
  typeFormule: 'widow char',
  codeEtat: 'beside likewise within',
};

export const sampleWithFullData: IProduit = {
  id: 2644,
  codeProduit: 'towards always oh',
  dateAdhesionProduit: dayjs('2025-08-22'),
  dateRadiationProduit: dayjs('2025-08-21'),
  codeFormule: 'given cash',
  codeFamilleRisqueFormule: 'manipulate pearl free',
  titreFormule: 'formamide freely',
  typeFormule: 'unnecessarily',
  codeEtat: 'phew unless',
};

export const sampleWithNewData: NewProduit = {
  codeProduit: 'altruistic mean',
  dateAdhesionProduit: dayjs('2025-08-21'),
  codeFormule: 'ouch',
  codeFamilleRisqueFormule: 'prioritize',
  titreFormule: 'bravely which quirkily',
  typeFormule: 'detain inside',
  codeEtat: 'in-joke',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
