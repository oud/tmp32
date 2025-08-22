package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ContratTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Contrat getContratSample1() {
        return new Contrat()
            .id(1L)
            .numeroContratCollectif("numeroContratCollectif1")
            .migre("migre1")
            .codeEntiteRattachement("codeEntiteRattachement1")
            .codeCentreGestion("codeCentreGestion1")
            .codeGroupeGestion("codeGroupeGestion1")
            .codeReseauDistribution("codeReseauDistribution1")
            .typeContratCollectif("typeContratCollectif1")
            .etatContrat("etatContrat1")
            .motifResiliation("motifResiliation1")
            .codeNatureCouverture("codeNatureCouverture1")
            .codeOffre("codeOffre1")
            .numeroVersionOffre("numeroVersionOffre1")
            .echeancePrincipale("echeancePrincipale1")
            .codeOrganismePorteurRisque("codeOrganismePorteurRisque1")
            .indicateurPorteurRisque("indicateurPorteurRisque1")
            .codeOrganismeProducteurFicheDsn("codeOrganismeProducteurFicheDsn1")
            .codeOrganismeDelegataireCotisations("codeOrganismeDelegataireCotisations1")
            .codeOrganismeDelegatairePrestations("codeOrganismeDelegatairePrestations1")
            .datePremierMoisCotisationAutorise("datePremierMoisCotisationAutorise1")
            .numeroOperationNiveau0(1)
            .statut("statut1");
    }

    public static Contrat getContratSample2() {
        return new Contrat()
            .id(2L)
            .numeroContratCollectif("numeroContratCollectif2")
            .migre("migre2")
            .codeEntiteRattachement("codeEntiteRattachement2")
            .codeCentreGestion("codeCentreGestion2")
            .codeGroupeGestion("codeGroupeGestion2")
            .codeReseauDistribution("codeReseauDistribution2")
            .typeContratCollectif("typeContratCollectif2")
            .etatContrat("etatContrat2")
            .motifResiliation("motifResiliation2")
            .codeNatureCouverture("codeNatureCouverture2")
            .codeOffre("codeOffre2")
            .numeroVersionOffre("numeroVersionOffre2")
            .echeancePrincipale("echeancePrincipale2")
            .codeOrganismePorteurRisque("codeOrganismePorteurRisque2")
            .indicateurPorteurRisque("indicateurPorteurRisque2")
            .codeOrganismeProducteurFicheDsn("codeOrganismeProducteurFicheDsn2")
            .codeOrganismeDelegataireCotisations("codeOrganismeDelegataireCotisations2")
            .codeOrganismeDelegatairePrestations("codeOrganismeDelegatairePrestations2")
            .datePremierMoisCotisationAutorise("datePremierMoisCotisationAutorise2")
            .numeroOperationNiveau0(2)
            .statut("statut2");
    }

    public static Contrat getContratRandomSampleGenerator() {
        return new Contrat()
            .id(longCount.incrementAndGet())
            .numeroContratCollectif(UUID.randomUUID().toString())
            .migre(UUID.randomUUID().toString())
            .codeEntiteRattachement(UUID.randomUUID().toString())
            .codeCentreGestion(UUID.randomUUID().toString())
            .codeGroupeGestion(UUID.randomUUID().toString())
            .codeReseauDistribution(UUID.randomUUID().toString())
            .typeContratCollectif(UUID.randomUUID().toString())
            .etatContrat(UUID.randomUUID().toString())
            .motifResiliation(UUID.randomUUID().toString())
            .codeNatureCouverture(UUID.randomUUID().toString())
            .codeOffre(UUID.randomUUID().toString())
            .numeroVersionOffre(UUID.randomUUID().toString())
            .echeancePrincipale(UUID.randomUUID().toString())
            .codeOrganismePorteurRisque(UUID.randomUUID().toString())
            .indicateurPorteurRisque(UUID.randomUUID().toString())
            .codeOrganismeProducteurFicheDsn(UUID.randomUUID().toString())
            .codeOrganismeDelegataireCotisations(UUID.randomUUID().toString())
            .codeOrganismeDelegatairePrestations(UUID.randomUUID().toString())
            .datePremierMoisCotisationAutorise(UUID.randomUUID().toString())
            .numeroOperationNiveau0(intCount.incrementAndGet())
            .statut(UUID.randomUUID().toString());
    }
}
