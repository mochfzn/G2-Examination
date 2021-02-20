package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.StockOpname;
import com.nexsoft.minimarket.service.BarangService;
import com.nexsoft.minimarket.service.StockOpnameService;
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
public class StockOpnameController {
    public static final Logger logger = LoggerFactory.getLogger(StockOpnameController.class);

    @Autowired
    StockOpnameService stockOpnameService;

    @Autowired
    BarangService barangService;

    @GetMapping("/stock-opname/")
    public ResponseEntity<List<StockOpname>> listAllStockOpname() {
        List<StockOpname> stockOpnameList = stockOpnameService.findAll();

        if (stockOpnameList.isEmpty()) {
            return new ResponseEntity<>(stockOpnameList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockOpnameList, HttpStatus.OK);
    }

    @GetMapping("/stock-opname/{id}")
    public ResponseEntity<?> getStockOpname(@PathVariable("id") String id) {
        logger.info("Fetching stock opname with id {}", id);

        StockOpname stockOpname = stockOpnameService.findById(id);

        if(stockOpname == null) {
            logger.error("Stock opname with id {} not found", id);
            return new ResponseEntity<>(new CustomErrorType("Stock opname with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockOpname, HttpStatus.OK);
    }

    @PostMapping("/stock-opname")
    public ResponseEntity<?> createStockOpname(@RequestBody StockOpname stockOpname) {
        logger.info("Creating stock opname : {}", stockOpname);

        if(!stockOpnameService.emptyValidation(stockOpname))
        {
            logger.error("Unable to create. There is empty field in stock opname object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in stock opname object."), HttpStatus.FORBIDDEN);
        }
        else if(!barangService.emptyValidation(stockOpname.getBarang()))
        {
            logger.error("Unable to create. There is empty field in stock opname object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in stock opname object."), HttpStatus.FORBIDDEN);
        }

        stockOpnameService.save(stockOpname);
        return new ResponseEntity<>(stockOpname, HttpStatus.CREATED);
    }

    @PutMapping("/stock-opname/{id}")
    public ResponseEntity<?> updateStockOpname(@PathVariable("id") String id, @RequestBody StockOpname stockOpname) {
        logger.info("Updating stock opname : {}", stockOpname);

        if(!stockOpnameService.emptyValidation(stockOpname))
        {
            logger.error("Unable to create. There is empty field in stock opname object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in stock opname object."), HttpStatus.FORBIDDEN);
        }
        else if(!barangService.emptyValidation(stockOpname.getBarang()))
        {
            logger.error("Unable to create. There is empty field in stock opname object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in stock opname object."), HttpStatus.FORBIDDEN);
        }

        stockOpnameService.update(stockOpname, id);
        return new ResponseEntity<>(stockOpname, HttpStatus.OK);
    }

    @DeleteMapping("/stock-opname/{id}")
    public ResponseEntity<?> deleteStockOpname(@PathVariable("id") String id) {
        logger.info("Fetching & deleting stock opname with id {}", id);

        StockOpname stockOpname = stockOpnameService.findById(id);

        if(stockOpname == null) {
            logger.error("Unable to delete. Stock opname with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Stock opname with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        stockOpnameService.deleteById(id);
        return new ResponseEntity<>("Stock opname with id" + id + "has been deleted successfully", HttpStatus.OK);
    }
}
