import { IMiseEnGestion } from 'app/entities/mise-en-gestion/mise-en-gestion.model';
import { Status } from 'app/entities/enumerations/status.model';

export interface IDemandeXRM {
  id: number;
  dateDemande?: string | null;
  dateEnvoiAI?: string | null;
  dateEnvoiIVS?: string | null;
  aI?: keyof typeof Status | null;
  iVS?: keyof typeof Status | null;
  miseEnGestions?: Pick<IMiseEnGestion, 'id'>[] | null;
}

export type NewDemandeXRM = Omit<IDemandeXRM, 'id'> & { id: null };
