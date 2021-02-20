package com.nexsoft.minimarket.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StockOpname {
    private String id;
    private Barang barang;
    private String alasan;
    private Date waktu;
    private int jumlah;

    public StockOpname() {
    }

    public StockOpname(String id, String alasan, Date waktu, int jumlah) {
        this.id = id;
        this.alasan = alasan;
        this.waktu = waktu;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return "StockOpname{" +
                "id='" + id + '\'' +
                ", barang=" + barang +
                ", alasan='" + alasan + '\'' +
                ", waktu=" + waktu.getTime() +
                ", jumlah=" + jumlah +
                '}';
    }
}
