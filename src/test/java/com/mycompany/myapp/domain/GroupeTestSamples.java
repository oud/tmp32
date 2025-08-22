package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GroupeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Groupe getGroupeSample1() {
        return new Groupe()
            .id(1L)
            .codeGroupeAssures("codeGroupeAssures1")
            .codeGroupePopulation("codeGroupePopulation1")
            .typeGroupeAssures("typeGroupeAssures1")
            .libelleGroupeAssuresTypeAutre("libelleGroupeAssuresTypeAutre1")
            .codeEtatGroupeAssures("codeEtatGroupeAssures1");
    }

    public static Groupe getGroupeSample2() {
        return new Groupe()
            .id(2L)
            .codeGroupeAssures("codeGroupeAssures2")
            .codeGroupePopulation("codeGroupePopulation2")
            .typeGroupeAssures("typeGroupeAssures2")
            .libelleGroupeAssuresTypeAutre("libelleGroupeAssuresTypeAutre2")
            .codeEtatGroupeAssures("codeEtatGroupeAssures2");
    }

    public static Groupe getGroupeRandomSampleGenerator() {
        return new Groupe()
            .id(longCount.incrementAndGet())
            .codeGroupeAssures(UUID.randomUUID().toString())
            .codeGroupePopulation(UUID.randomUUID().toString())
            .typeGroupeAssures(UUID.randomUUID().toString())
            .libelleGroupeAssuresTypeAutre(UUID.randomUUID().toString())
            .codeEtatGroupeAssures(UUID.randomUUID().toString());
    }
}
