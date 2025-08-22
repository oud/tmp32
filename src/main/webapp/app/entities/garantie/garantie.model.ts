import dayjs from 'dayjs/esm';
import { IProduit } from 'app/entities/produit/produit.model';

export interface IGarantie {
  id: number;
  codeGarantieTechnique?: string | null;
  codeEtatGarantie?: string | null;
  dateAdhesionGarantie?: dayjs.Dayjs | null;
  dateRadiationGarantie?: dayjs.Dayjs | null;
  codeAssureur?: string | null;
  codeFormule?: string | null;
  codePack?: string | null;
  typePack?: string | null;
  titrePack?: string | null;
  codeSousPack?: string | null;
  typeSousPack?: string | null;
  titreSousPack?: string | null;
  codeTypePrestations?: string | null;
  produit?: Pick<IProduit, 'id'> | null;
}

export type NewGarantie = Omit<IGarantie, 'id'> & { id: null };
