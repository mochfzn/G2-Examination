package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.model.Kasir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("KasirRepository")
public class KasirRepositoryImpl implements KasirRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Kasir> findAll() {
        List<Kasir> kasirList;

        try {
            kasirList = this.jdbcTemplate.query("SELECT * FROM kasir",
                    (rs, rowNum) ->
                            new Kasir(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            ));
        } catch (IndexOutOfBoundsException e) {
            kasirList = null;
        }

        return kasirList;
    }

    @Override
    public Kasir findById(String id) {
        Kasir kasir;

        try {
            kasir = this.jdbcTemplate.query(
                    "SELECT * FROM kasir WHERE id = ?",
                    preparedStatement -> preparedStatement.setString(1, id),
                    (rs, rowNum) ->
                            new Kasir(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            ).get(0);
        } catch(IndexOutOfBoundsException e) {
            kasir = null;
        }

        return kasir;
    }

    @Override
    public List<Kasir> findByName(String nama) {
        List<Kasir> kasirList;

        try {
            kasirList = jdbcTemplate.query(
                    "SELECT * FROM kasir WHERE nama LIKE ?",
                    preparedStatement -> preparedStatement.setString(1, "%" + nama + "%"),
                    (rs, rowNum) ->
                            new Kasir(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            );
        } catch (IndexOutOfBoundsException e) {
            kasirList = null;
        }

        return kasirList;
    }

    @Override
    public Kasir findByNameSpesific(String nama) {
        Kasir kasir;

        try {
            kasir = jdbcTemplate.query(
                    "SELECT * FROM kasir WHERE nama = ?",
                    preparedStatement -> preparedStatement.setString(1, nama),
                    (rs, rowNum) ->
                            new Kasir(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getString("alamat"),
                                    rs.getString("telepon")
                            )
            ).get(0);
        } catch (IndexOutOfBoundsException e) {
            kasir = null;
        }

        return kasir;
    }

    @Override
    public int insert(Kasir kasir) {
        return jdbcTemplate.update(
                "INSERT INTO kasir(id, nama, alamat, telepon) values(?, ?, ?, ?)",
                kasir.getId(), kasir.getNama(), kasir.getAlamat(), kasir.getTelepon()
        );
    }

    @Override
    public int update(Kasir kasir) {
        return jdbcTemplate.update(
                "UPDATE kasir SET nama = ?, alamat = ?, telepon = ? WHERE id = ?",
                kasir.getNama(), kasir.getAlamat(), kasir.getTelepon(), kasir.getId()
        );
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(
                "DELETE FROM kasir WHERE id = ?", id
        );
    }
}
