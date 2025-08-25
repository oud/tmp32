package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.AdresseTestSamples.*;
import static com.mycompany.myapp.domain.EmailTestSamples.*;
import static com.mycompany.myapp.domain.GroupeTestSamples.*;
import static com.mycompany.myapp.domain.MiseEnGestionTestSamples.*;
import static com.mycompany.myapp.domain.PmEntrepriseTestSamples.*;
import static com.mycompany.myapp.domain.PmEtablissementTestSamples.*;
import static com.mycompany.myapp.domain.TelephoneTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PmEtablissementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PmEtablissement.class);
        PmEtablissement pmEtablissement1 = getPmEtablissementSample1();
        PmEtablissement pmEtablissement2 = new PmEtablissement();
        assertThat(pmEtablissement1).isNotEqualTo(pmEtablissement2);

        pmEtablissement2.setId(pmEtablissement1.getId());
        assertThat(pmEtablissement1).isEqualTo(pmEtablissement2);

        pmEtablissement2 = getPmEtablissementSample2();
        assertThat(pmEtablissement1).isNotEqualTo(pmEtablissement2);
    }

    @Test
    void adresseTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        Adresse adresseBack = getAdresseRandomSampleGenerator();

        pmEtablissement.addAdresse(adresseBack);
        assertThat(pmEtablissement.getAdresses()).containsOnly(adresseBack);
        assertThat(adresseBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.removeAdresse(adresseBack);
        assertThat(pmEtablissement.getAdresses()).doesNotContain(adresseBack);
        assertThat(adresseBack.getPmEtablissement()).isNull();

        pmEtablissement.adresses(new HashSet<>(Set.of(adresseBack)));
        assertThat(pmEtablissement.getAdresses()).containsOnly(adresseBack);
        assertThat(adresseBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.setAdresses(new HashSet<>());
        assertThat(pmEtablissement.getAdresses()).doesNotContain(adresseBack);
        assertThat(adresseBack.getPmEtablissement()).isNull();
    }

    @Test
    void emailTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        Email emailBack = getEmailRandomSampleGenerator();

        pmEtablissement.addEmail(emailBack);
        assertThat(pmEtablissement.getEmails()).containsOnly(emailBack);
        assertThat(emailBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.removeEmail(emailBack);
        assertThat(pmEtablissement.getEmails()).doesNotContain(emailBack);
        assertThat(emailBack.getPmEtablissement()).isNull();

        pmEtablissement.emails(new HashSet<>(Set.of(emailBack)));
        assertThat(pmEtablissement.getEmails()).containsOnly(emailBack);
        assertThat(emailBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.setEmails(new HashSet<>());
        assertThat(pmEtablissement.getEmails()).doesNotContain(emailBack);
        assertThat(emailBack.getPmEtablissement()).isNull();
    }

    @Test
    void telephoneTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        Telephone telephoneBack = getTelephoneRandomSampleGenerator();

        pmEtablissement.addTelephone(telephoneBack);
        assertThat(pmEtablissement.getTelephones()).containsOnly(telephoneBack);
        assertThat(telephoneBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.removeTelephone(telephoneBack);
        assertThat(pmEtablissement.getTelephones()).doesNotContain(telephoneBack);
        assertThat(telephoneBack.getPmEtablissement()).isNull();

        pmEtablissement.telephones(new HashSet<>(Set.of(telephoneBack)));
        assertThat(pmEtablissement.getTelephones()).containsOnly(telephoneBack);
        assertThat(telephoneBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.setTelephones(new HashSet<>());
        assertThat(pmEtablissement.getTelephones()).doesNotContain(telephoneBack);
        assertThat(telephoneBack.getPmEtablissement()).isNull();
    }

    @Test
    void miseEnGestionTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        MiseEnGestion miseEnGestionBack = getMiseEnGestionRandomSampleGenerator();

        pmEtablissement.addMiseEnGestion(miseEnGestionBack);
        assertThat(pmEtablissement.getMiseEnGestions()).containsOnly(miseEnGestionBack);
        assertThat(miseEnGestionBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.removeMiseEnGestion(miseEnGestionBack);
        assertThat(pmEtablissement.getMiseEnGestions()).doesNotContain(miseEnGestionBack);
        assertThat(miseEnGestionBack.getPmEtablissement()).isNull();

        pmEtablissement.miseEnGestions(new HashSet<>(Set.of(miseEnGestionBack)));
        assertThat(pmEtablissement.getMiseEnGestions()).containsOnly(miseEnGestionBack);
        assertThat(miseEnGestionBack.getPmEtablissement()).isEqualTo(pmEtablissement);

        pmEtablissement.setMiseEnGestions(new HashSet<>());
        assertThat(pmEtablissement.getMiseEnGestions()).doesNotContain(miseEnGestionBack);
        assertThat(miseEnGestionBack.getPmEtablissement()).isNull();
    }

    @Test
    void groupeTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        Groupe groupeBack = getGroupeRandomSampleGenerator();

        pmEtablissement.setGroupe(groupeBack);
        assertThat(pmEtablissement.getGroupe()).isEqualTo(groupeBack);

        pmEtablissement.groupe(null);
        assertThat(pmEtablissement.getGroupe()).isNull();
    }

    @Test
    void pmEntrepriseTest() {
        PmEtablissement pmEtablissement = getPmEtablissementRandomSampleGenerator();
        PmEntreprise pmEntrepriseBack = getPmEntrepriseRandomSampleGenerator();

        pmEtablissement.setPmEntreprise(pmEntrepriseBack);
        assertThat(pmEtablissement.getPmEntreprise()).isEqualTo(pmEntrepriseBack);

        pmEtablissement.pmEntreprise(null);
        assertThat(pmEtablissement.getPmEntreprise()).isNull();
    }
}
