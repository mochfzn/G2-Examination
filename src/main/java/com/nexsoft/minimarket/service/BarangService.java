package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Barang;

import java.util.List;

public interface BarangService {
    List<Barang> findAll();
    Barang findById(String id);
    List<Barang> findByName(String nama);
    boolean save(Barang barang);
    Barang update(Barang barang, String id);
    boolean deleteById(String id);
    boolean isBarangExist(Barang barang);

    boolean emptyValidation(Barang barang);
}