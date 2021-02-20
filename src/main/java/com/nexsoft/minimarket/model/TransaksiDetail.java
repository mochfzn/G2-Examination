package com.nexsoft.minimarket.model;

public class TransaksiDetail {
    private String id;
    private Barang barang;
    private int jumlah;

    public TransaksiDetail() {
    }

    public TransaksiDetail(String id, int jumlah) {
        this.id = id;
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return "TransaksiDetail{" +
                "id='" + id + '\'' +
                ", barang='" + barang + '\'' +
                ", jumlah=" + jumlah +
                '}';
    }
}
