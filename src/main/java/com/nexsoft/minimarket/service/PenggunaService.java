package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Pengguna;

public interface PenggunaService {
    Pengguna login(String username, String password);
    void insert(Pengguna pengguna);
    void update(Pengguna pengguna);
    void delete(String id);
}
