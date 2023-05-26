package com.example.obapprest.controller;

import com.example.obapprest.entity.Laptop;
import com.example.obapprest.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private LaptopRepository laptopRepository;

    @Autowired
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok(this.laptopRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        try {
            Optional<Laptop> laptopOpt = this.laptopRepository.findById(id);
            return ResponseEntity.ok(laptopOpt.get());
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Laptop laptop,
                                    @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        try {
            Laptop laptopRes = this.laptopRepository.save(laptop);
            return ResponseEntity.ok(laptopRes);
        } catch (RuntimeException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Laptop laptop,
                                    @RequestHeader HttpHeaders headers){
        Map<String, Object> msBody = new HashMap<>();
        if (!this.laptopRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (this.laptopRepository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Laptop> laptopOPT = this.laptopRepository.findById(id);
        if (this.laptopRepository.existsById(id)) {
            laptopOPT.get().setModelo(laptop.getModelo());
            laptopOPT.get().setModelo(laptop.getModelo());
            this.laptopRepository.save(laptopOPT.get());
        }
        msBody.put("data", laptopOPT);
        return ResponseEntity.ok(msBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(!this.laptopRepository.existsById(id)){
            throw new NoSuchElementException("THE OBJECT DON'T EXISTS");
        }else{
            this.laptopRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(@RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(this.laptopRepository.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        this.laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}