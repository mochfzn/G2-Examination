package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Barang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BarangRepository")
public class BarangRepositoryImpl implements BarangRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Barang> findAll() {
        List<Barang> barangList;

        try {
            barangList = this.jdbcTemplate.query("SELECT * FROM barang",
                    (rs, rowNum) ->
                        new Barang(
                                rs.getString("id"),
                                rs.getString("nama"),
                                rs.getFloat("harga"),
                                rs.getInt("jumlah")
                        ));
        } catch (IndexOutOfBoundsException e) {
            barangList = null;
        }

        return barangList;
    }

    @Override
    public Barang findById(String id) {
        Barang barang;

        try {
            barang = this.jdbcTemplate.query(
                    "SELECT * FROM barang WHERE id = ?",
                    preparedStatement -> preparedStatement.setString(1, id),
                    (rs, rowNum) ->
                            new Barang(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getFloat("harga"),
                                    rs.getInt("jumlah")
                            )
            ).get(0);
        } catch(IndexOutOfBoundsException e) {
            barang = null;
        }

        return barang;
    }

    @Override
    public List<Barang> findByName(String nama) {
        List<Barang> barangList;

        try {
            barangList = jdbcTemplate.query(
                    "SELECT * FROM barang WHERE nama LIKE ?",
                    preparedStatement -> preparedStatement.setString(1, "%" + nama + "%"),
                            (rs, rowNum) ->
                                    new Barang(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getFloat("harga"),
                                            rs.getInt("jumlah")
                                    )
                    );
        } catch (IndexOutOfBoundsException e) {
            barangList = null;
        }

        return barangList;
    }

    @Override
    public Barang findByNameSpecific(String nama) {
        Barang barang;

        try {
            barang = jdbcTemplate.query(
                    "SELECT * FROM barang WHERE nama = ?",
                    preparedStatement -> preparedStatement.setString(1, nama),
                    (rs, rowNum) ->
                            new Barang(
                                    rs.getString("id"),
                                    rs.getString("nama"),
                                    rs.getFloat("harga"),
                                    rs.getInt("jumlah")
                            )
            ).get(0);
        } catch (IndexOutOfBoundsException e) {
            barang = null;
        }

        return barang;
    }

    @Override
    public int insert(Barang barang) {
        return jdbcTemplate.update(
                "INSERT INTO barang(id, nama, harga, jumlah) values(?, ?, ?, ?)",
                barang.getId(), barang.getNama(), barang.getHarga(), barang.getJumlah()
        );
    }

    @Override
    public int update(Barang barang) {
        return jdbcTemplate.update(
                "UPDATE barang SET nama = ?, harga = ?, jumlah = ? WHERE id = ?",
                barang.getNama(), barang.getHarga(), barang.getJumlah(), barang.getId()
        );
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(
                "DELETE FROM barang WHERE id = ?", id
        );
    }
}
