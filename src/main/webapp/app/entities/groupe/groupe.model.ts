import dayjs from 'dayjs/esm';
import { IContrat } from 'app/entities/contrat/contrat.model';

export interface IGroupe {
  id: number;
  codeGroupeAssures?: string | null;
  codeGroupePopulation?: string | null;
  typeGroupeAssures?: string | null;
  dateDebutPeriodeGroupeAssures?: dayjs.Dayjs | null;
  libelleGroupeAssuresTypeAutre?: string | null;
  codeEtatGroupeAssures?: string | null;
  contrat?: Pick<IContrat, 'id'> | null;
}

export type NewGroupe = Omit<IGroupe, 'id'> & { id: null };
