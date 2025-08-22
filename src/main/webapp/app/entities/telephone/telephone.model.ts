import dayjs from 'dayjs/esm';
import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';

export interface ITelephone {
  id: number;
  codeTypeTelephone?: string | null;
  codePaysISO?: string | null;
  dateDerniereModification?: dayjs.Dayjs | null;
  codeIndicatifPays?: string | null;
  numeroTelephone?: string | null;
  codeStatut?: string | null;
  dateStatut?: string | null;
  codeUsageTelephone?: string | null;
  pmEtablissement?: Pick<IPmEtablissement, 'id'> | null;
}

export type NewTelephone = Omit<ITelephone, 'id'> & { id: null };
