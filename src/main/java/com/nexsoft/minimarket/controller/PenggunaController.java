package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.Pengguna;
import com.nexsoft.minimarket.service.PenggunaService;
import com.nexsoft.minimarket.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/market")
public class PenggunaController {
    public static final Logger logger = LoggerFactory.getLogger(PenggunaController.class);

    @Autowired
    PenggunaService penggunaService;

    @GetMapping("/pengguna/")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        logger.info("Comparing data!");

        System.out.println("Username ==>" + username);
        System.out.println("Password ==>" + password);

        Pengguna pengguna = penggunaService.login(username, password);

        if(pengguna == null) {
            logger.error("Username or password is wrong!");
            return new ResponseEntity<>(new CustomErrorType("Username or password is wrong!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pengguna, HttpStatus.OK);
    }
}
