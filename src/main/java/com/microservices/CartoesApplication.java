package com.microservices;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class CartoesApplication {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}
