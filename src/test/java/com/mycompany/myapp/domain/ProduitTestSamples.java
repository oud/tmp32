package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProduitTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Produit getProduitSample1() {
        return new Produit()
            .id(1L)
            .codeProduit("codeProduit1")
            .codeFormule("codeFormule1")
            .codeFamilleRisqueFormule("codeFamilleRisqueFormule1")
            .titreFormule("titreFormule1")
            .typeFormule("typeFormule1")
            .codeEtat("codeEtat1");
    }

    public static Produit getProduitSample2() {
        return new Produit()
            .id(2L)
            .codeProduit("codeProduit2")
            .codeFormule("codeFormule2")
            .codeFamilleRisqueFormule("codeFamilleRisqueFormule2")
            .titreFormule("titreFormule2")
            .typeFormule("typeFormule2")
            .codeEtat("codeEtat2");
    }

    public static Produit getProduitRandomSampleGenerator() {
        return new Produit()
            .id(longCount.incrementAndGet())
            .codeProduit(UUID.randomUUID().toString())
            .codeFormule(UUID.randomUUID().toString())
            .codeFamilleRisqueFormule(UUID.randomUUID().toString())
            .titreFormule(UUID.randomUUID().toString())
            .typeFormule(UUID.randomUUID().toString())
            .codeEtat(UUID.randomUUID().toString());
    }
}
