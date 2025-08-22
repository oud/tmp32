import dayjs from 'dayjs/esm';
import { IPmEntreprise } from 'app/entities/pm-entreprise/pm-entreprise.model';

export interface IContrat {
  id: number;
  numeroContratCollectif?: string | null;
  migre?: string | null;
  codeEntiteRattachement?: string | null;
  codeCentreGestion?: string | null;
  codeGroupeGestion?: string | null;
  codeReseauDistribution?: string | null;
  typeContratCollectif?: string | null;
  etatContrat?: string | null;
  dateEffetPremiereSouscription?: dayjs.Dayjs | null;
  dateEffetDerniereResiliation?: dayjs.Dayjs | null;
  motifResiliation?: string | null;
  codeNatureCouverture?: string | null;
  codeOffre?: string | null;
  numeroVersionOffre?: string | null;
  echeancePrincipale?: string | null;
  codeOrganismePorteurRisque?: string | null;
  indicateurPorteurRisque?: string | null;
  codeOrganismeProducteurFicheDsn?: string | null;
  codeOrganismeDelegataireCotisations?: string | null;
  codeOrganismeDelegatairePrestations?: string | null;
  datePremierMoisCotisationAutorise?: string | null;
  numeroOperationNiveau0?: number | null;
  statut?: string | null;
  pmEntreprise?: Pick<IPmEntreprise, 'id'> | null;
}

export type NewContrat = Omit<IContrat, 'id'> & { id: null };
