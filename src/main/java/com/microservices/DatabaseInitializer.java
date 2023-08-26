package com.microservices;

import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DatabaseInitializer {

    @Inject
    PgPool client;

    public void onStart(@Observes StartupEvent ev) {
        initDb();
    }

    private void initDb() {
        // Crie a tabela
        String createTableSql = "CREATE TABLE IF NOT EXISTS cartoes (" +
                                "id SERIAL PRIMARY KEY," +
                                "nome VARCHAR(255)," +
                                "numero VARCHAR(16)," +
                                "validade DATE" +
                                ");"; 
        client.query(createTableSql).execute()
            .await().indefinitely();

        // Insira dados iniciais
        String insertDataSql = "INSERT INTO cartoes (nome, numero, validade) VALUES ('Emerson Amorim', '1234567812345678', '2081-18-02');"; 
        client.query(insertDataSql).execute()
            .await().indefinitely();
    }
}
