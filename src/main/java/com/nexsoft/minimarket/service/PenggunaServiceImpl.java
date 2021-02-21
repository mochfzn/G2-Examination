package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Pengguna;
import com.nexsoft.minimarket.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PenggunaService")
public class PenggunaServiceImpl implements PenggunaService {
    @Autowired
    PenggunaRepository penggunaRepository;

    @Override
    public Pengguna login(String username, String password) {
        return penggunaRepository.login(username, password);
    }

    @Override
    public void insert(Pengguna pengguna) {

    }

    @Override
    public void update(Pengguna pengguna) {

    }

    @Override
    public void delete(String id) {

    }
}
