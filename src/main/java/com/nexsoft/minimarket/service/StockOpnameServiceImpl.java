package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.model.StockOpname;
import com.nexsoft.minimarket.repository.BarangRepository;
import com.nexsoft.minimarket.repository.StockOpnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("StockOpnameService")
public class StockOpnameServiceImpl implements StockOpnameService {
    @Autowired
    StockOpnameRepository stockOpnameRepository;

    @Override
    public List<StockOpname> findAll() {
        return stockOpnameRepository.findAll();
    }

    @Override
    public StockOpname findById(String id) {
        return stockOpnameRepository.findById(id);
    }

    @Override
    public void save(StockOpname stockOpname) {
        UUID stockOpnameId = UUID.randomUUID();
        stockOpname.setId(stockOpnameId.toString());

        synchronized (this) {
            stockOpnameRepository.insert(stockOpname);
        }
    }

    @Override
    public void update(StockOpname stockOpname, String id) {
        StockOpname current = stockOpnameRepository.findById(id);

        current.setBarang(stockOpname.getBarang());
        current.setWaktu(stockOpname.getWaktu());
        current.setJumlah(stockOpname.getJumlah());
        current.setAlasan(stockOpname.getAlasan());

        synchronized (this) {
            stockOpnameRepository.update(current);
        }
    }

    @Override
    public void deleteById(String id) {
        synchronized (this) {
            stockOpnameRepository.delete(id);
        }
    }

    @Override
    public boolean emptyValidation(StockOpname stockOpname) {
        if(stockOpname.getBarang() == null)
        {
            return false;
        }
        else if(stockOpname.getJumlah() == 0)
        {
            return false;
        }
        else if(stockOpname.getWaktu().toString().isBlank())
        {
            return false;
        }
        else if(stockOpname.getAlasan().isBlank())
        {
            return false;
        }

        return true;
    }
}
