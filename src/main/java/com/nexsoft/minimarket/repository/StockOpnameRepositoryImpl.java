package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.model.StockOpname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StockOpnameRepository")
public class StockOpnameRepositoryImpl implements StockOpnameRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<StockOpname> findAll() {
        List<StockOpname> stockOpnameList;

        try {
            stockOpnameList = this.jdbcTemplate.query("SELECT * FROM stock_opname",
                    (rs, rowNum) ->
                            new StockOpname(
                                    rs.getString("id"),
                                    rs.getString("alasan"),
                                    rs.getDate("waktu"),
                                    rs.getInt("jumlah")
                            ));

            for(StockOpname objek: stockOpnameList) {
                objek.setBarang(
                        this.jdbcTemplate.query("SELECT b.id, b.nama, b.harga, b.jumlah FROM barang b, stock_opname s WHERE b.id = s.id_barang AND s.id = ?",
                                preparedStatement -> preparedStatement.setString(1, objek.getId()),
                                (rs, rowNum) ->
                                    new Barang(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getFloat("harga"),
                                            rs.getInt("jumlah")
                                    )).get(0)
                );
            }
        } catch (IndexOutOfBoundsException e) {
            stockOpnameList = null;
        }

        return stockOpnameList;
    }

    @Override
    public StockOpname findById(String id) {

        try {
            StockOpname stockOpname;
            stockOpname = this.jdbcTemplate.query(
                    "SELECT * FROM stock_opname WHERE id = ?",
                    preparedStatement -> preparedStatement.setString(1, id),
                    (rs, rowNum) ->
                            new StockOpname(
                                    rs.getString("id"),
                                    rs.getString("alasan"),
                                    rs.getDate("waktu"),
                                    rs.getInt("jumlah")
                            )
            ).get(0);

            stockOpname.setBarang(
                    this.jdbcTemplate.query("SELECT b.id, b.nama, b.harga, b.jumlah FROM barang b, stock_opname s WHERE b.id = s.id_barang AND s.id = ?",
                            preparedStatement -> preparedStatement.setString(1, stockOpname.getId()),
                            (rs, rowNum) ->
                                    new Barang(
                                            rs.getString("id"),
                                            rs.getString("nama"),
                                            rs.getFloat("harga"),
                                            rs.getInt("jumlah")
                                    )).get(0)
            );

            return stockOpname;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int insert(StockOpname stockOpname) {
        return jdbcTemplate.update(
                "INSERT INTO stock_opname(id, id_barang, alasan, waktu, jumlah) values(?, ?, ?, ?, ?)",
                stockOpname.getId(), stockOpname.getBarang().getId(), stockOpname.getAlasan(), new java.sql.Date(stockOpname.getWaktu().getTime()), stockOpname.getJumlah()
        );
    }

    @Override
    public int update(StockOpname stockOpname) {
        return jdbcTemplate.update(
                "UPDATE stock_opname SET alasan = ?, waktu = ?, jumlah = ?, id_barang = ? WHERE id = ?",
                stockOpname.getAlasan(), stockOpname.getWaktu(), stockOpname.getJumlah(), stockOpname.getBarang().getId(), stockOpname.getId()
        );
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(
                "DELETE FROM stock_opname WHERE id = ?", id
        );
    }
}
