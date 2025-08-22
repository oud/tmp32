package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GarantieTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Garantie getGarantieSample1() {
        return new Garantie()
            .id(1L)
            .codeGarantieTechnique("codeGarantieTechnique1")
            .codeEtatGarantie("codeEtatGarantie1")
            .codeAssureur("codeAssureur1")
            .codeFormule("codeFormule1")
            .codePack("codePack1")
            .typePack("typePack1")
            .titrePack("titrePack1")
            .codeSousPack("codeSousPack1")
            .typeSousPack("typeSousPack1")
            .titreSousPack("titreSousPack1")
            .codeTypePrestations("codeTypePrestations1");
    }

    public static Garantie getGarantieSample2() {
        return new Garantie()
            .id(2L)
            .codeGarantieTechnique("codeGarantieTechnique2")
            .codeEtatGarantie("codeEtatGarantie2")
            .codeAssureur("codeAssureur2")
            .codeFormule("codeFormule2")
            .codePack("codePack2")
            .typePack("typePack2")
            .titrePack("titrePack2")
            .codeSousPack("codeSousPack2")
            .typeSousPack("typeSousPack2")
            .titreSousPack("titreSousPack2")
            .codeTypePrestations("codeTypePrestations2");
    }

    public static Garantie getGarantieRandomSampleGenerator() {
        return new Garantie()
            .id(longCount.incrementAndGet())
            .codeGarantieTechnique(UUID.randomUUID().toString())
            .codeEtatGarantie(UUID.randomUUID().toString())
            .codeAssureur(UUID.randomUUID().toString())
            .codeFormule(UUID.randomUUID().toString())
            .codePack(UUID.randomUUID().toString())
            .typePack(UUID.randomUUID().toString())
            .titrePack(UUID.randomUUID().toString())
            .codeSousPack(UUID.randomUUID().toString())
            .typeSousPack(UUID.randomUUID().toString())
            .titreSousPack(UUID.randomUUID().toString())
            .codeTypePrestations(UUID.randomUUID().toString());
    }
}
