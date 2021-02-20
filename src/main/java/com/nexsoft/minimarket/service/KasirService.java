package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Kasir;

import java.util.List;

public interface KasirService {
    List<Kasir> findAll();
    Kasir findById(String id);
    List<Kasir> findByName(String nama);
    void save(Kasir kasir);
    Kasir update(Kasir kasir, String id);
    void deleteById(String id);
    boolean isKasirExist(Kasir kasir);

    boolean emptyValidation(Kasir kasir);
}
