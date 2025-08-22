import dayjs from 'dayjs/esm';
import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { IDemandeXRM } from 'app/entities/demande-xrm/demande-xrm.model';

export interface IMiseEnGestion {
  id: number;
  codeTypeMiseEnGestion?: string | null;
  codeOffre?: string | null;
  dateEffet?: dayjs.Dayjs | null;
  pmEtablissement?: Pick<IPmEtablissement, 'id'> | null;
  demandeXRM?: Pick<IDemandeXRM, 'id'> | null;
}

export type NewMiseEnGestion = Omit<IMiseEnGestion, 'id'> & { id: null };
