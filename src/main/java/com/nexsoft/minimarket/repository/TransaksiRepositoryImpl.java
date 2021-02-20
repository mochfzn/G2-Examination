package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("TransaksiRepository")
public class TransaksiRepositoryImpl implements TransaksiRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaksi> findAll() {
        List<Transaksi> transaksiList;

        try {
            transaksiList = this.jdbcTemplate.query("SELECT * FROM transaksi",
                    (rs, rowNum) ->
                            new Transaksi(
                                    rs.getString("id"),
                                    rs.getDate("waktu"),
                                    rs.getFloat("total")
                            ));

            for(Transaksi objek: transaksiList) {
                objek.setCustomer(
                        this.jdbcTemplate.query("SELECT c.id, c.nama, c.alamat, c.telepon FROM customer c, transaksi t WHERE c.id = t.id_customer AND t.id = ?",
                                preparedStatement -> preparedStatement.setString(1, objek.getId()),
                                (rs, rowNum) ->
                                        new Customer(
                                                rs.getString("id"),
                                                rs.getString("nama"),
                                                rs.getString("alamat"),
                                                rs.getString("telepon")
                                        )).get(0)
                );

                objek.setKasir(
                        this.jdbcTemplate.query("SELECT k.id, k.nama, k.alamat, k.telepon FROM kasir k, transaksi t WHERE k.id = t.id_kasir AND t.id = ?",
                                preparedStatement -> preparedStatement.setString(1, objek.getId()),
                                (rs, rowNum) ->
                                        new Kasir(
                                                rs.getString("id"),
                                                rs.getString("nama"),
                                                rs.getString("alamat"),
                                                rs.getString("telepon")
                                        )).get(0)
                );

                objek.setDetail(
                        this.jdbcTemplate.query("SELECT * FROM transaksi_detail WHERE id_transaksi = ?",
                                preparedStatement -> preparedStatement.setString(1, objek.getId()),
                                (rs, rowNum) ->
                                        new TransaksiDetail(
                                                rs.getString("id"),
                                                rs.getInt("jumlah")
                                        )
                        )
                );

                for(TransaksiDetail detil: objek.getDetail()) {
                    detil.setBarang(
                            this.jdbcTemplate.query("SELECT b.id, b.nama, b.harga, b.jumlah FROM barang b, transaksi_detail d WHERE b.id = d.id_barang AND d.id = ?",
                                    preparedStatement -> preparedStatement.setString(1, detil.getId()),
                                    (rs, rowNum) ->
                                            new Barang(
                                                    rs.getString("id"),
                                                    rs.getString("nama"),
                                                    rs.getFloat("harga"),
                                                    rs.getInt("jumlah")
                                            )
                            ).get(0)
                    );
                }
            }
        } catch (IndexOutOfBoundsException e) {
            transaksiList = null;
        }

        return transaksiList;
    }

    @Override
    public Transaksi findById(String id) {
        try {
            Transaksi transaksi;
            transaksi = this.jdbcTemplate.query(
                    "SELECT * FROM transaksi WHERE id = ?",
                    preparedStatement -> preparedStatement.setString(1, id),
                    (rs, rowNum) ->
                            new Transaksi(
                                    rs.getString("id"),
                                    rs.getDate("waktu"),
                                    rs.getFloat("total")
                            )
            ).get(0);

            transaksi.setCustomer(
                    this.jdbcTemplate.query("SELECT c.id, c.nama, c.alamat, c.telepon FROM customer c, transaksi t WHERE c.id = t.id_customer AND t.id = ?",
                            preparedStatement -> preparedStatement.setString(1, transaksi.getId()),
                            (rs, rowNum) ->
                                    new Customer(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getString("alamat"),
                                            rs.getString("telepon")
                                    )).get(0)
            );

            transaksi.setKasir(
                    this.jdbcTemplate.query("SELECT k.id, k.nama, k.alamat, k.telepon FROM kasir k, transaksi t WHERE k.id = t.id_kasir AND t.id = ?",
                            preparedStatement -> preparedStatement.setString(1, transaksi.getId()),
                            (rs, rowNum) ->
                                    new Kasir(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getString("alamat"),
                                            rs.getString("telepon")
                                    )).get(0)
            );

            transaksi.setDetail(
                    this.jdbcTemplate.query("SELECT * FROM transaksi_detail WHERE id_transaksi = ?",
                            preparedStatement -> preparedStatement.setString(1, transaksi.getId()),
                            (rs, rowNum) ->
                                    new TransaksiDetail(
                                            rs.getString("id"),
                                            rs.getInt("jumlah")
                                    )
                    )
            );

            for(TransaksiDetail detil: transaksi.getDetail()) {
                detil.setBarang(
                        this.jdbcTemplate.query("SELECT b.id, b.nama, b.harga, b.jumlah FROM barang b, transaksi_detail d WHERE b.id = d.id_barang AND d.id = ?",
                                preparedStatement -> preparedStatement.setString(1, detil.getId()),
                                (rs, rowNum) ->
                                        new Barang(
                                                rs.getString("id"),
                                                rs.getString("nama"),
                                                rs.getFloat("harga"),
                                                rs.getInt("jumlah")
                                        )
                        ).get(0)
                );
            }

            return transaksi;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int insert(Transaksi transaksi) {
        jdbcTemplate.update(
                "INSERT INTO transaksi(id, waktu, total, id_customer, id_kasir) values(?, ?, ?, ?, ?)",
                transaksi.getId(), transaksi.getWaktu(), transaksi.getTotal(), transaksi.getCustomer().getId(), transaksi.getKasir().getId()
        );

        for(TransaksiDetail detail: transaksi.getDetail())
        {
            UUID detailId = UUID.randomUUID();
            detail.setId(detailId.toString());
            jdbcTemplate.update("INSERT INTO transaksi_detail(id, id_transaksi, id_barang, jumlah) VALUES(?, ?, ?, ?)",
                    detail.getId(), transaksi.getId(), detail.getBarang().getId(), detail.getJumlah());
        }

        return 1;
    }

    @Override
    public int update(Transaksi transaksi) {
        jdbcTemplate.update("DELETE FROM transaksi_detail WHERE id_transaksi= ?", transaksi.getId());

        for(TransaksiDetail detail: transaksi.getDetail())
        {
            UUID detailId = UUID.randomUUID();
            detail.setId(detailId.toString());
            jdbcTemplate.update("INSERT INTO transaksi_detail(id, id_transaksi, id_barang, jumlah) VALUES(?, ?, ?, ?)",
                    detail.getId(), transaksi.getId(), detail.getBarang().getId(), detail.getJumlah());
        }

        return 1;
    }

    @Override
    public int delete(String id) {
        jdbcTemplate.update(
                "DELETE FROM transaksi_detail WHERE id_transaksi = ?", id
        );

        jdbcTemplate.update(
                "DELETE FROM transaksi WHERE id = ?", id
        );

        return 1;
    }
}
