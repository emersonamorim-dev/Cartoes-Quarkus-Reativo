package com.microservices.Repository;

import com.microservices.Models.Cartoes;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartoesRepository {

    @Inject
    PgPool client;

    public Multi<Cartoes> findAll() {
        return client.query("SELECT * FROM cartoes")
                     .execute()
                     .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                     .onItem().transform(this::fromRowToCartoes);
    }

    public Uni<Cartoes> findById(Long id) {
        return client.preparedQuery("SELECT * FROM cartoes WHERE id = $1")
                     .execute(Tuple.of(id))
                     .onItem().transform(set -> set.iterator().hasNext() ? fromRowToCartoes(set.iterator().next()) : null);
    }

    public Uni<Long> save(Cartoes cartao) {
        return client.preparedQuery("INSERT INTO cartoes (nome, numero, validade, codigoSeguranca) VALUES ($1, $2, $3, $4) RETURNING id")
                     .execute(Tuple.of(cartao.getNome(), cartao.getNumero(), cartao.getValidade(), cartao.getCodigoSeguranca()))
                     .onItem().transform(set -> set.iterator().next().getLong("id"));
    }

    public Uni<Cartoes> update(Long id, Cartoes cartao) {
        return client.preparedQuery("UPDATE cartoes SET nome=$1, numero=$2, validade=$3, codigoSeguranca=$4 WHERE id=$5 RETURNING *")
                     .execute(Tuple.of(cartao.getNome(), cartao.getNumero(), cartao.getValidade(), cartao.getCodigoSeguranca(), id))
                     .onItem().transform(set -> set.iterator().hasNext() ? fromRowToCartoes(set.iterator().next()) : null);
    }

    public Uni<Boolean> delete(Long id) {
        return client.preparedQuery("DELETE FROM cartoes WHERE id = $1")
                     .execute(Tuple.of(id))
                     .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    public Multi<Cartoes> findDebitoCartoes() {
        return client.query("SELECT * FROM cartoes WHERE tipo = 'debito'")
                     .execute()
                     .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                     .onItem().transform(this::fromRowToCartoes);
    }

    public Multi<Cartoes> findCreditoCartoes() {
        return client.query("SELECT * FROM cartoes WHERE tipo = 'credito'")
                     .execute()
                     .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                     .onItem().transform(this::fromRowToCartoes);
    }


    private Cartoes fromRowToCartoes(Row row) {
        Long id = row.getLong("id");
        String nome = row.getString("nome");
        String numero = row.getString("numero");
        String validade = row.getString("validade");
        String codigoSeguranca = row.getString("codigoSeguranca");
    
        return new Cartoes(id, nome, numero, validade, codigoSeguranca);
    }
    
}

