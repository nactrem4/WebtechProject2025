package com.example.demo.web;


import com.example.demo.service.TastaturService;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

}
