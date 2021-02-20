package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.StockOpname;

import java.util.List;

public interface StockOpnameRepository {
    List<StockOpname> findAll();
    StockOpname findById(String id);
    int insert(StockOpname stockOpname);
    int update(StockOpname stockOpname);
    int delete(String id);
}
