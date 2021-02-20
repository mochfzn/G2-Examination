package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Kasir;
import com.nexsoft.minimarket.repository.KasirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("KasirService")
public class KasirServiceImpl implements KasirService {
    @Autowired
    KasirRepository kasirRepository;

    @Override
    public List<Kasir> findAll() {
        return kasirRepository.findAll();
    }

    @Override
    public Kasir findById(String id) {
        return kasirRepository.findById(id);
    }

    @Override
    public List<Kasir> findByName(String nama) {
        return kasirRepository.findByName(nama);
    }

    @Override
    public void save(Kasir kasir) {
        UUID kasirId = UUID.randomUUID();
        kasir.setId(kasirId.toString());

        synchronized (this) {
            kasirRepository.insert(kasir);
        }
    }

    @Override
    public Kasir update(Kasir kasir, String id) {
        Kasir currentKasir = kasirRepository.findById(id);

        currentKasir.setNama(kasir.getNama());
        currentKasir.setAlamat(kasir.getAlamat());
        currentKasir.setTelepon(kasir.getTelepon());

        synchronized (this) {
            kasirRepository.update(currentKasir);
        }

        return currentKasir;
    }

    @Override
    public void deleteById(String id) {
        synchronized (this) {
            kasirRepository.delete(id);
        }
    }

    @Override
    public boolean isKasirExist(Kasir kasir) {
        return kasirRepository.findByNameSpesific(kasir.getNama()) != null;
    }

    @Override
    public boolean emptyValidation(Kasir kasir) {
        if(kasir.getNama().isBlank())
        {
            return false;
        }
        else if(kasir.getAlamat().isBlank())
        {
            return false;
        }
        else if(kasir.getTelepon().isBlank())
        {
            return false;
        }

        return true;
    }
}
