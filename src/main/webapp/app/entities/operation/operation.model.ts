import dayjs from 'dayjs/esm';
import { IContrat } from 'app/entities/contrat/contrat.model';

export interface IOperation {
  id: number;
  numeroOperationNiveau0?: string | null;
  etatOperation?: string | null;
  dateEffetOperation?: dayjs.Dayjs | null;
  dateDemandeOperation?: dayjs.Dayjs | null;
  dateCreation?: dayjs.Dayjs | null;
  codeActeGestion?: string | null;
  numeroOperationAnnulee?: string | null;
  contrat?: Pick<IContrat, 'id'> | null;
}

export type NewOperation = Omit<IOperation, 'id'> & { id: null };
