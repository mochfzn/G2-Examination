package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.model.Kasir;
import com.nexsoft.minimarket.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("PenggunaRepository")
public class PenggunaRepositoryImpl implements PenggunaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Pengguna login(String username, String password) {

        try {
            Pengguna pengguna;
            pengguna = this.jdbcTemplate.query(
                    "SELECT * FROM pengguna WHERE username = ? AND password = ?",
                    preparedStatement -> {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                    },
                    (rs, rowNum) ->
                            new Pengguna(
                                    rs.getString("id"),
                                    rs.getString("akses"),
                                    rs.getString("username"),
                                    rs.getString("password")
                            )
            ).get(0);

            pengguna.setKasir(
                    this.jdbcTemplate.query(
                            "SELECT k.id, k.nama, k.alamat, k.telepon FROM kasir k, pengguna p WHERE k.id = p.id_kasir AND p.id = ?",
                            preparedStatement -> preparedStatement.setString(1, pengguna.getId()),
                            (rs, rowNum) ->
                                    new Kasir(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getString("alamat"),
                                            rs.getString("telepon")
                                    )
                    ).get(0)
            );

            System.out.println("Pemeriksaan ====>" + pengguna);

            return pengguna;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int insert(Pengguna pengguna) {
        return 0;
    }

    @Override
    public int update(Pengguna pengguna) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
