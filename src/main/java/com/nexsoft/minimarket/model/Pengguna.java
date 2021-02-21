package com.nexsoft.minimarket.model;

public class Pengguna {
    private String id;
    private Kasir kasir;
    private String akses;
    private String username;
    private String password;

    public Pengguna() {
    }

    public Pengguna(String id, String akses, String username, String password) {
        this.id = id;
        this.akses = akses;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Pengguna{" +
                "id='" + id + '\'' +
                ", kasir=" + kasir +
                ", akses='" + akses + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
