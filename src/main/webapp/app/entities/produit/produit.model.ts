import dayjs from 'dayjs/esm';
import { IGroupe } from 'app/entities/groupe/groupe.model';

export interface IProduit {
  id: number;
  codeProduit?: string | null;
  dateAdhesionProduit?: dayjs.Dayjs | null;
  dateRadiationProduit?: dayjs.Dayjs | null;
  codeFormule?: string | null;
  codeFamilleRisqueFormule?: string | null;
  titreFormule?: string | null;
  typeFormule?: string | null;
  codeEtat?: string | null;
  groupe?: Pick<IGroupe, 'id'> | null;
}

export type NewProduit = Omit<IProduit, 'id'> & { id: null };
