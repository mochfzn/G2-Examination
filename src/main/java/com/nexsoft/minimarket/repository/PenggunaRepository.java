package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Pengguna;

public interface PenggunaRepository {
    Pengguna login(String username, String password);
    int insert(Pengguna pengguna);
    int update(Pengguna pengguna);
    int delete(String id);
}
