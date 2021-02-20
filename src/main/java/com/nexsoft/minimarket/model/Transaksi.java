package com.nexsoft.minimarket.model;

import java.util.List;
import java.util.Date;

public class Transaksi {
    private String id;
    private Date waktu;
    private float total;
    private Customer customer;
    private Kasir kasir;
    private List<TransaksiDetail> detail;

    public Transaksi() {
    }

    public Transaksi(String id, Date waktu, float total) {
        this.id = id;
        this.waktu = waktu;
        this.total = total;
        this.customer = null;
        this.kasir = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TransaksiDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<TransaksiDetail> detail) {
        this.detail = detail;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "id='" + id + '\'' +
                ", waktu=" + waktu +
                ", total=" + total +
                ", customer=" + customer +
                ", kasir=" + kasir +
                ", detail=" + detail +
                '}';
    }
}
