package com.microservices.Services;

import com.microservices.Models.Cartoes;
import com.microservices.Repository.CartoesRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartoesService {

    @Inject
    CartoesRepository cartoesRepository;

    public Multi<Cartoes> getAllCartoes() {
        return cartoesRepository.findAll();
    }

    public Uni<Cartoes> getCartaoById(Long id) {
        return cartoesRepository.findById(id);
    }

    public Uni<Long> createCartao(Cartoes cartao) {
        return cartoesRepository.save(cartao);
    }

    public Uni<Cartoes> updateCartao(Long id, Cartoes cartao) {
        return cartoesRepository.update(id, cartao);
    }

    public Uni<Boolean> deleteCartao(Long id) {
        return cartoesRepository.delete(id);
    }

    public Multi<Cartoes> getDebitoCartoes() {
        return cartoesRepository.findDebitoCartoes();
    }

    public Multi<Cartoes> getCreditoCartoes() {
        return cartoesRepository.findCreditoCartoes();
    }
}
