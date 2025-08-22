package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OperationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Operation getOperationSample1() {
        return new Operation()
            .id(1L)
            .numeroOperationNiveau0("numeroOperationNiveau01")
            .etatOperation("etatOperation1")
            .codeActeGestion("codeActeGestion1")
            .numeroOperationAnnulee("numeroOperationAnnulee1");
    }

    public static Operation getOperationSample2() {
        return new Operation()
            .id(2L)
            .numeroOperationNiveau0("numeroOperationNiveau02")
            .etatOperation("etatOperation2")
            .codeActeGestion("codeActeGestion2")
            .numeroOperationAnnulee("numeroOperationAnnulee2");
    }

    public static Operation getOperationRandomSampleGenerator() {
        return new Operation()
            .id(longCount.incrementAndGet())
            .numeroOperationNiveau0(UUID.randomUUID().toString())
            .etatOperation(UUID.randomUUID().toString())
            .codeActeGestion(UUID.randomUUID().toString())
            .numeroOperationAnnulee(UUID.randomUUID().toString());
    }
}
