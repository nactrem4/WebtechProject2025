package com.example.demo.web;


import com.example.demo.persistence.TastaturEntität;
import com.example.demo.service.TastaturService;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TastaturRestController {

    private final TastaturService tastaturService;

    public TastaturRestController(TastaturService tastaturService) {
        this.tastaturService = tastaturService;
    }

    @GetMapping(path = "/tastaturen")
    public ResponseEntity<List<Tastatur>> fetchTastaturen() {
        return ResponseEntity.ok(tastaturService.findall());
    }

    @GetMapping(path = "/tastaturen/{id}")
    public ResponseEntity<Tastatur> fetchTastaturById(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        return tastatur != null? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/tastaturen")
    public ResponseEntity<Void> createTastatur(@RequestBody TastaturCreateRequest request) throws URISyntaxException {
    var tastatur = tastaturService.create(request);
    URI uri = new URI("/tastaturen/" + tastatur.getId());
    return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/tastaturen/{id}")
    public ResponseEntity<Tastatur> updateTastatur(@PathVariable Long id, @RequestBody TastaturCreateRequest request) {
        var tastatur = tastaturService.update(id, request);
        return tastatur != null? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }
    @DeleteMapping(path = "/tastaturen/{id}")
    public ResponseEntity<Void> deleteTastatur(@PathVariable Long id) {
        boolean successful = tastaturService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    @GetMapping(path = "/tastaturen/{id}/bild")
    public ResponseEntity<byte[]> getTastaturBild(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        if (tastatur == null || tastatur.getBild() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg") // Oder passend zu deinem Bildformat
                .body(tastatur.getBild());
    }

}
