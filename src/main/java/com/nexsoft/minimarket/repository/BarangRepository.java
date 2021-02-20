package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Barang;

import java.util.List;

public interface BarangRepository {
    List<Barang> findAll();
    Barang findById(String id);
    List<Barang> findByName(String nama);
    Barang findByNameSpecific(String nama);
    int insert(Barang barang);
    int update(Barang barang);
    int delete(String id);
}
