package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Transaksi;

import java.util.List;

public interface TransaksiRepository {
    List<Transaksi> findAll();
    Transaksi findById(String id);
    int insert(Transaksi transaksi);
    int update(Transaksi transaksi);
    int delete(String id);
}
