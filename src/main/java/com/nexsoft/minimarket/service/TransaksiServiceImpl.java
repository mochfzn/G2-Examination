package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.*;
import com.nexsoft.minimarket.repository.BarangRepository;
import com.nexsoft.minimarket.repository.CustomerRepository;
import com.nexsoft.minimarket.repository.KasirRepository;
import com.nexsoft.minimarket.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("TransaksiService")
public class TransaksiServiceImpl implements TransaksiService {
    @Autowired
    TransaksiRepository transaksiRepository;

    @Autowired
    BarangRepository barangRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    KasirRepository kasirRepository;

    @Override
    public List<Transaksi> findAll() {
        return transaksiRepository.findAll();
    }

    @Override
    public Transaksi findById(String id) {
        return transaksiRepository.findById(id);
    }

    @Override
    public Boolean save(Transaksi transaksi) {
        UUID transaksiId = UUID.randomUUID();
        transaksi.setId(transaksiId.toString());

        synchronized (this) {
            transaksiRepository.insert(transaksi);
        }

        return true;
    }

    @Override
    public Boolean update(Transaksi transaksi) {
        synchronized (this) {
            transaksiRepository.update(transaksi);
        }

        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        synchronized (this) {
            transaksiRepository.delete(id);
        }

        return false;
    }

    @Override
    public Customer customerValidation(Customer customer) {
        Customer cekCustomer = customerRepository.findById(customer.getId());

        if(cekCustomer == null) {
            return customer;
        } else {
            return null;
        }
    }

    @Override
    public Kasir kasirValidation(Kasir kasir) {
        Kasir cekKasir = kasirRepository.findById(kasir.getId());

        if(cekKasir == null) {
            return kasir;
        } else {
            return null;
        }
    }

    @Override
    public Barang barangValidation(Transaksi transaksi) {
        for(TransaksiDetail detail: transaksi.getDetail())
        {
            Barang barang = barangRepository.findById(detail.getBarang().getId());
            if(barang == null) {
                return detail.getBarang();
            }
        }

        return null;
    }

    @Override
    public Boolean emptyValidation(Transaksi transaksi) {
        if(transaksi.getWaktu() ==  null)
        {
            return false;
        }
        else if(transaksi.getTotal() == 0.0f)
        {
            return false;
        }
        else if(transaksi.getCustomer() == null)
        {
            return false;
        }
        else if(transaksi.getKasir() == null)
        {
            return false;
        }
        else if(transaksi.getDetail().isEmpty())
        {
            return false;
        }

        return true;
    }

    @Override
    public Boolean emptyValidationFromDetail(TransaksiDetail detail) {
        if(detail.getJumlah() == 0)
        {
            return false;
        }
        else if(detail.getBarang() == null)
        {
            return false;
        }

        return true;
    }
}
