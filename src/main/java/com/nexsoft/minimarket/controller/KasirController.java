package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.Kasir;
import com.nexsoft.minimarket.service.KasirService;
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
public class KasirController {
    public static final Logger logger = LoggerFactory.getLogger(KasirController.class);

    @Autowired
    KasirService kasirService;

    @GetMapping("/kasir/")
    public ResponseEntity<List<Kasir>> listAllKasir() {
        List<Kasir> kasirList = kasirService.findAll();

        if(kasirList.isEmpty()) {
            return new ResponseEntity<>(kasirList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(kasirList, HttpStatus.OK);
    }

    @GetMapping("/kasir/{id}")
    public ResponseEntity<?> getKasir(@PathVariable("id") String id) {
        logger.info("Fetching kasir with id {}", id);

        Kasir kasir = kasirService.findById(id);

        if(kasir == null) {
            logger.error("kasir with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Kasir with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(kasir, HttpStatus.OK);
    }

    @GetMapping("/kasir/nama/{nama}")
    public ResponseEntity<?> getKasirByName(@PathVariable("nama") String nama) {
        logger.info("Fetching kasir with name {}", nama);

        List<Kasir> kasirList = kasirService.findByName(nama);

        if(kasirList == null) {
            logger.error("kasir with name {} not found.", nama);
            return new ResponseEntity<>(new CustomErrorType("Kasir with name " + nama + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(kasirList, HttpStatus.OK);
    }

    @PostMapping("/kasir/")
    public ResponseEntity<?> createKasir(@RequestBody Kasir kasir) {
        logger.info("Creating Kasir : {}", kasir);

        if(!kasirService.emptyValidation(kasir))
        {
            logger.error("Unable to create. There is empty field in kasir object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(kasirService.isKasirExist(kasir))
        {
            logger.error("Unable to create. A kasir with name {} already exist", kasir.getNama());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A kasir with name " + kasir.getNama() + " already exist."), HttpStatus.CONFLICT);
        }

        kasirService.save(kasir);
        return new ResponseEntity<>(kasir, HttpStatus.CREATED);
    }

    @PutMapping("/kasir/{id}")
    public ResponseEntity<?> updateKasir(@PathVariable("id") String id, @RequestBody Kasir kasir) {
        logger.info("Updating kasir with id {}", id);

        Kasir kasirFound = kasirService.findById(id);
        if(!kasirService.emptyValidation(kasir))
        {
            logger.error("Unable to create. There is empty field in kasir object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(kasirFound == null) {
            logger.error("Unable to update. Kasir with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Kasir with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        Kasir currentKasir = kasirService.update(kasir, id);
        return new ResponseEntity<>(currentKasir, HttpStatus.OK);
    }

    @DeleteMapping("/kasir/{id}")
    public ResponseEntity<?> deleteKasir(@PathVariable("id") String id) {
        logger.info("Fetching & Deleting kasir with id {}", id);

        Kasir kasir = kasirService.findById(id);

        if(kasir == null) {
            logger.error("Unable to delete. Kasir with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Kasir with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        kasirService.deleteById(id);
        return new ResponseEntity<>("Kasir with id" + id + "has been deleted successfully", HttpStatus.OK);
    }
}
