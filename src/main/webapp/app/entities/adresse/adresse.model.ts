import dayjs from 'dayjs/esm';
import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';

export interface IAdresse {
  id: number;
  codePaysISO?: string | null;
  codePostal?: string | null;
  dateDerniereModification?: dayjs.Dayjs | null;
  codeTypeAdresse?: string | null;
  libelleCommune?: string | null;
  ligne1?: string | null;
  ligne2?: string | null;
  ligne3?: string | null;
  ligne4?: string | null;
  ligne5?: string | null;
  ligne6?: string | null;
  ligne7?: string | null;
  nombreCourriersPND?: string | null;
  codeUsageAdresse?: string | null;
  pmEtablissement?: Pick<IPmEtablissement, 'id'> | null;
}

export type NewAdresse = Omit<IAdresse, 'id'> & { id: null };
