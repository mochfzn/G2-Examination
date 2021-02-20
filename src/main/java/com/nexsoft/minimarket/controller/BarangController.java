package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.Barang;
import com.nexsoft.minimarket.service.BarangService;
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
public class BarangController {
    public static final Logger logger = LoggerFactory.getLogger(BarangController.class);

    @Autowired
    BarangService barangService;

    @GetMapping("/barang/")
    public ResponseEntity<List<Barang>> listAllBarang() {
        List<Barang> barangList = barangService.findAll();

        if(barangList.isEmpty()) {
            return new ResponseEntity<>(barangList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(barangList, HttpStatus.OK);
    }

    @GetMapping("/barang/{id}")
    public ResponseEntity<?> getBarang(@PathVariable("id") String id) {
        logger.info("Fetching barang with id {}", id);
        Barang barang = barangService.findById(id);

        if(barang == null) {
            logger.error("barang with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Barang with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(barang, HttpStatus.OK);
    }

    @GetMapping("/barang/nama/{nama}")
    public ResponseEntity<?> getBarangByName(@PathVariable("nama") String nama) {
        logger.info("Fetching barang with name {}", nama);
        List<Barang> barangList = barangService.findByName(nama);

        if(barangList == null) {
            logger.error("barang with name {} not found.", nama);
            return new ResponseEntity<>(new CustomErrorType("Barang with name " + nama + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(barangList, HttpStatus.OK);
    }

    @PostMapping("/barang/")
    public ResponseEntity<?> createBarang(@RequestBody Barang barang) {
        logger.info("Creating barang : {}", barang);

        boolean empty = barangService.emptyValidation(barang);
        boolean cek = barangService.save(barang);

        if(!empty)
        {
            logger.error("Unable to create. There is empty field in barang object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in barang object."), HttpStatus.FORBIDDEN);
        }
        else if(!cek)
        {
            logger.error("Unable to create. A barang with name {} already exist", barang.getNama());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A barang with name " + barang.getNama() + " already exist."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(barang, HttpStatus.CREATED);
    }

    @PutMapping("/barang/{id}")
    public ResponseEntity<?> updateBarang(@PathVariable("id") String id, @RequestBody Barang barang) {
        logger.info("Updating barang with id {}", id);

        boolean empty = barangService.emptyValidation(barang);
        Barang currentBarang = barangService.update(barang, id);

        if(!empty)
        {
            logger.error("Unable to create. There is empty field in barang object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in barang object."), HttpStatus.FORBIDDEN);
        }
        else if(currentBarang == null)
        {
            logger.error("Unable to update. Barang with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. barang with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentBarang, HttpStatus.OK);
    }

    @DeleteMapping("/barang/{id}")
    public ResponseEntity<?> deleteBarang(@PathVariable("id") String  id) {
        logger.info("Fetching & deleting Pangkat with id {}", id);

        boolean hasil = barangService.deleteById(id);

        if(!hasil) {
            logger.error("Unable to delete. Barang with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Barang with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Barang with id" + id + "has been deleted successfully", HttpStatus.OK);
    }
}
