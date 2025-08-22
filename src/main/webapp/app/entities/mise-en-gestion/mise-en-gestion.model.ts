import dayjs from 'dayjs/esm';
import { IDemandeXRM } from 'app/entities/demande-xrm/demande-xrm.model';
import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';

export interface IMiseEnGestion {
  id: number;
  codeTypeMiseEnGestion?: string | null;
  codeOffre?: string | null;
  dateEffet?: dayjs.Dayjs | null;
  demandeXRMS?: Pick<IDemandeXRM, 'id'>[] | null;
  pmEtablissements?: Pick<IPmEtablissement, 'id'>[] | null;
}

export type NewMiseEnGestion = Omit<IMiseEnGestion, 'id'> & { id: null };
