package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.*;

import java.util.List;

public interface TransaksiService {
    List<Transaksi> findAll();
    Transaksi findById(String id);
    Boolean save(Transaksi transaksi);
    Boolean update(Transaksi transaksi);
    Boolean deleteById(String id);

    Customer customerValidation(Customer customer);
    Kasir kasirValidation(Kasir kasir);
    Barang barangValidation(Transaksi transaksi);
    Boolean emptyValidation(Transaksi transaksi);
    Boolean emptyValidationFromDetail(TransaksiDetail detail);
}
