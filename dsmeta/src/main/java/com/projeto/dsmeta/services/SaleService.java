package com.projeto.dsmeta.services;

import com.projeto.dsmeta.entities.Sale;
import com.projeto.dsmeta.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public List<Sale> findSales(){
       return repository.findAll();
    }
}
