package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.StockOpname;

import java.util.List;

public interface StockOpnameService {
    List<StockOpname> findAll();
    StockOpname findById(String id);
    void save(StockOpname stockOpname);
    void update(StockOpname stockOpname, String id);
    void deleteById(String id);

    boolean emptyValidation(StockOpname stockOpname);
}
