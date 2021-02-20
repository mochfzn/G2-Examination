package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.*;
import com.nexsoft.minimarket.service.BarangService;
import com.nexsoft.minimarket.service.CustomerService;
import com.nexsoft.minimarket.service.KasirService;
import com.nexsoft.minimarket.service.TransaksiService;
import com.nexsoft.minimarket.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/market")
public class TransaksiController {
    public static final Logger logger = LoggerFactory.getLogger(StockOpnameController.class);

    @Autowired
    TransaksiService transaksiService;

    @Autowired
    BarangService barangService;

    @Autowired
    CustomerService customerService;

    @Autowired
    KasirService kasirService;

    @GetMapping("/transaksi/")
    public ResponseEntity<List<Transaksi>> listAllTransaksi() {
        List<Transaksi> transaksiList = transaksiService.findAll();

        if(transaksiList.isEmpty()) {
            return new ResponseEntity<>(transaksiList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaksiList, HttpStatus.OK);
    }

    @GetMapping("/transaksi/{id}")
    public ResponseEntity<?> getTransaksi(@PathVariable("id") String id) {
        logger.info("Fetching transaksi with id {}", id);

        Transaksi transaksi = transaksiService.findById(id);

        if(transaksi == null) {
            logger.error("Transaksi with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Transaksi with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaksi, HttpStatus.OK);
    }

    @PostMapping("/transaksi/")
    public ResponseEntity<?> createTransaksi(@RequestBody Transaksi transaksi) {
        logger.info("Creating transaksi : {}", transaksi);

        /*
        * Melakukan pemeriksaan pada Customer dan Kasir,
        * kemudian dilanjutkan dengan pemeriksaan pada setiap barang yang dimasukkan dari transaksi detail.
        */

        if(!transaksiService.emptyValidation(transaksi))
        {
            logger.error("Unable to create. There is empty field in transaksi object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in transaksi object."), HttpStatus.FORBIDDEN);
        }
        else if(!customerService.emptyValidation(transaksi.getCustomer()))
        {
            logger.error("Unable to create. There is empty field in customer object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(!kasirService.emptyValidation(transaksi.getKasir()))
        {
            logger.error("Unable to create. There is empty field in kasir object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in kasir object."), HttpStatus.FORBIDDEN);
        }
        else
        {
            for(TransaksiDetail detail: transaksi.getDetail())
            {
                if(!transaksiService.emptyValidationFromDetail(detail))
                {
                    logger.error("Unable to create. There is empty field in transaksi detail object.");
                    return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in transaksi detail object."), HttpStatus.FORBIDDEN);
                }
                else if(!barangService.emptyValidation(detail.getBarang()))
                {
                    logger.error("Unable to create. There is empty field in barang object.");
                    return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in barang detail object."), HttpStatus.FORBIDDEN);
                }
            }
        }

        Customer cekCustomer = transaksiService.customerValidation(transaksi.getCustomer());
        Kasir cekKasir = transaksiService.kasirValidation(transaksi.getKasir());
        Barang cekBarang = transaksiService.barangValidation(transaksi);

        if(cekCustomer != null)
        {
            logger.error("Customer with id {} not found.", cekCustomer.getId());
            return new ResponseEntity<>(new CustomErrorType("Customer with id " + cekCustomer.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(cekKasir != null)
        {
            logger.error("Kasir with id {} not found.", cekKasir.getId());
            return new ResponseEntity<>(new CustomErrorType("Kasir with id " + cekKasir.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(cekBarang != null)
        {
            logger.error("Barang with id {} not found.", cekBarang.getId());
            return new ResponseEntity<>(new CustomErrorType("Barang with id " + cekBarang.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else
        {
            transaksiService.save(transaksi);
            return new ResponseEntity<>(transaksi, HttpStatus.CREATED);
        }
    }

    @PutMapping("/transaksi/{id}")
    public ResponseEntity<?> updateTransaksi(@PathVariable("id") String id, @RequestBody Transaksi transaksi) {
        logger.info("Updating transaksi with id {}", id);

        /*
         * Melakukan pemeriksaan pada Customer dan Kasir,
         * kemudian dilanjutkan dengan pemeriksaan pada setiap barang yang dimasukkan dari transaksi detail.
         */

        if(!transaksiService.emptyValidation(transaksi))
        {
            logger.error("Unable to create. There is empty field in transaksi object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in transaksi object."), HttpStatus.FORBIDDEN);
        }
        else if(!customerService.emptyValidation(transaksi.getCustomer()))
        {
            logger.error("Unable to create. There is empty field in customer object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(!kasirService.emptyValidation(transaksi.getKasir()))
        {
            logger.error("Unable to create. There is empty field in kasir object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in kasir object."), HttpStatus.FORBIDDEN);
        }
        else
        {
            for(TransaksiDetail detail: transaksi.getDetail())
            {
                if(!transaksiService.emptyValidationFromDetail(detail))
                {
                    logger.error("Unable to create. There is empty field in transaksi detail object.");
                    return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in transaksi detail object."), HttpStatus.FORBIDDEN);
                }
                else if(!barangService.emptyValidation(detail.getBarang()))
                {
                    logger.error("Unable to create. There is empty field in barang object.");
                    return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in barang detail object."), HttpStatus.FORBIDDEN);
                }
            }
        }

        Transaksi cekTransaksi = transaksiService.findById(id);
        Customer cekCustomer = transaksiService.customerValidation(transaksi.getCustomer());
        Kasir cekKasir = transaksiService.kasirValidation(transaksi.getKasir());
        Barang cekBarang = transaksiService.barangValidation(transaksi);

        if(cekTransaksi == null)
        {
            logger.error("Transaksi with id {} not found.", cekTransaksi.getId());
            return new ResponseEntity<>(new CustomErrorType("Transaksi with id " + cekTransaksi.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(cekCustomer != null)
        {
            logger.error("Customer with id {} not found.", cekCustomer.getId());
            return new ResponseEntity<>(new CustomErrorType("Customer with id " + cekCustomer.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(cekKasir != null)
        {
            logger.error("Kasir with id {} not found.", cekKasir.getId());
            return new ResponseEntity<>(new CustomErrorType("Kasir with id " + cekKasir.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else if(cekBarang != null)
        {
            logger.error("Barang with id {} not found.", cekBarang.getId());
            return new ResponseEntity<>(new CustomErrorType("Barang with id " + cekBarang.getId() + " not found"), HttpStatus.NOT_FOUND);
        }
        else
        {
            transaksiService.update(transaksi);
            return new ResponseEntity<>(transaksi, HttpStatus.OK);
        }
    }

    @DeleteMapping("/transaksi/{id}")
    public ResponseEntity<?> deleteTransaksi(@PathVariable("id") String id) {
        logger.info("Fetching & deleting transaksi with id {}", id);

        Transaksi transaksi = transaksiService.findById(id);
        if(transaksi == null) {
            logger.error("Unable to delete. Transaksi with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Transaksi with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        transaksiService.deleteById(transaksi.getId());
        return new ResponseEntity<>("Transaksi with id" + id + " has been deleted successfully", HttpStatus.OK);
    }
}
