import { IGarantie } from 'app/entities/garantie/garantie.model';

export interface ITaux {
  id: number;
  codeVariableDeclarative?: string | null;
  uniteVariableDeclarative?: string | null;
  valeurFacteurMontant?: string | null;
  valeurFacteurTaux?: string | null;
  garantie?: Pick<IGarantie, 'id'> | null;
}

export type NewTaux = Omit<ITaux, 'id'> & { id: null };
