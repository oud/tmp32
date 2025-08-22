package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TauxTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Taux getTauxSample1() {
        return new Taux()
            .id(1L)
            .codeVariableDeclarative("codeVariableDeclarative1")
            .uniteVariableDeclarative("uniteVariableDeclarative1")
            .valeurFacteurMontant("valeurFacteurMontant1")
            .valeurFacteurTaux("valeurFacteurTaux1");
    }

    public static Taux getTauxSample2() {
        return new Taux()
            .id(2L)
            .codeVariableDeclarative("codeVariableDeclarative2")
            .uniteVariableDeclarative("uniteVariableDeclarative2")
            .valeurFacteurMontant("valeurFacteurMontant2")
            .valeurFacteurTaux("valeurFacteurTaux2");
    }

    public static Taux getTauxRandomSampleGenerator() {
        return new Taux()
            .id(longCount.incrementAndGet())
            .codeVariableDeclarative(UUID.randomUUID().toString())
            .uniteVariableDeclarative(UUID.randomUUID().toString())
            .valeurFacteurMontant(UUID.randomUUID().toString())
            .valeurFacteurTaux(UUID.randomUUID().toString());
    }
}
