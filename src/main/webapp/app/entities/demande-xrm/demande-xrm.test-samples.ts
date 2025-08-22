import { IDemandeXRM, NewDemandeXRM } from './demande-xrm.model';

export const sampleWithRequiredData: IDemandeXRM = {
  id: 23981,
};

export const sampleWithPartialData: IDemandeXRM = {
  id: 4754,
  dateDemande: '14:53:00',
  dateEnvoiIVS: '20:48:00',
  iVS: 'TRAITE',
};

export const sampleWithFullData: IDemandeXRM = {
  id: 16434,
  dateDemande: '15:11:00',
  dateEnvoiAI: '20:36:00',
  dateEnvoiIVS: '12:47:00',
  aI: 'TRAITE',
  iVS: 'RECU',
};

export const sampleWithNewData: NewDemandeXRM = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
