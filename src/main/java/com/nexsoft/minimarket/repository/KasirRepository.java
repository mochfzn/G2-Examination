package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Kasir;

import java.util.List;

public interface KasirRepository {
    List<Kasir> findAll();
    Kasir findById(String id);
    List<Kasir> findByName(String nama);
    Kasir findByNameSpesific(String nama);
    int insert(Kasir kasir);
    int update(Kasir kasir);
    int delete(String id);
}
