import { ITaux, NewTaux } from './taux.model';

export const sampleWithRequiredData: ITaux = {
  id: 25853,
  codeVariableDeclarative: 'whose usable',
  uniteVariableDeclarative: 'and but enormously',
  valeurFacteurTaux: 'ack kinase likewise',
};

export const sampleWithPartialData: ITaux = {
  id: 21120,
  codeVariableDeclarative: 'director knife',
  uniteVariableDeclarative: 'metabolise if',
  valeurFacteurTaux: 'milestone',
};

export const sampleWithFullData: ITaux = {
  id: 7041,
  codeVariableDeclarative: 'around',
  uniteVariableDeclarative: 'digit brightly deploy',
  valeurFacteurMontant: 'extricate',
  valeurFacteurTaux: 'ultimate amused',
};

export const sampleWithNewData: NewTaux = {
  codeVariableDeclarative: 'mid sophisticated bah',
  uniteVariableDeclarative: 'memorise accidentally sorrowful',
  valeurFacteurTaux: 'rewarding',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
