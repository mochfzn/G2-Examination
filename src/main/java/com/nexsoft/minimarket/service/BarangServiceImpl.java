package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("BarangService")
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangRepository barangRepository;

    @Override
    public List<Barang> findAll() {
        return barangRepository.findAll();
    }

    @Override
    public Barang findById(String id) {
        return barangRepository.findById(id);
    }

    @Override
    public List<Barang> findByName(String nama) {
        return barangRepository.findByName(nama);
    }

    @Override
    public boolean save(Barang barang) {
        boolean hasil;

        if(!isBarangExist(barang))
        {
            UUID barangId = UUID.randomUUID();
            barang.setId(barangId.toString());

            synchronized (this) {
                barangRepository.insert(barang);
            }

            hasil = true;
        } else {
            hasil = false;
        }

        return hasil;
    }

    @Override
    public Barang update(Barang barang, String id) {
        Barang currentBarang = barangRepository.findById(id);

        if(currentBarang != null) {
            currentBarang.setNama(barang.getNama());
            currentBarang.setHarga(barang.getHarga());
            currentBarang.setJumlah(barang.getJumlah());

            synchronized (this) {
                barangRepository.update(currentBarang);
            }
        } else {
            currentBarang = null;
        }

        return currentBarang;
    }

    @Override
    public boolean deleteById(String id) {
        boolean hasil;
        Barang barangFound = barangRepository.findById(id);

        if(barangFound != null) {
            synchronized (this) {
                barangRepository.delete(id);
            }

            hasil = true;
        } else {
            hasil = false;
        }

        return hasil;
    }

    @Override
    public boolean isBarangExist(Barang barang) {
        return barangRepository.findByNameSpecific(barang.getNama()) != null;
    }

    @Override
    public boolean emptyValidation(Barang barang) {
        if(barang.getNama().isBlank())
        {
            return false;
        }
        else if(barang.getHarga() == 0.0f)
        {
            return false;
        }
        else if(barang.getJumlah() == 0)
        {
            return false;
        }

        return true;
    }
}
