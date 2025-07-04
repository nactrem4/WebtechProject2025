package com.example.demo.web;

import com.example.demo.service.TastaturService;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tastaturen")
public class TastaturRestController {

    private final TastaturService tastaturService;

    public TastaturRestController(TastaturService tastaturService) {
        this.tastaturService = tastaturService;
    }

    @GetMapping
    public ResponseEntity<List<Tastatur>> fetchTastaturen() {
        return ResponseEntity.ok(tastaturService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tastatur> fetchTastaturById(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        return tastatur != null ? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createTastatur(@RequestBody TastaturCreateRequest request) throws URISyntaxException {
        var tastatur = tastaturService.create(request);
        URI uri = new URI("/tastaturen/" + tastatur.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tastatur> updateTastatur(@PathVariable Long id, @RequestBody TastaturCreateRequest request) {
        var tastatur = tastaturService.update(id, request);
        return tastatur != null ? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTastatur(@PathVariable Long id) {
        boolean successful = tastaturService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/bild")
    public ResponseEntity<byte[]> getTastaturBild(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        if (tastatur == null || tastatur.getBild() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(tastatur.getBild());
    }

    // Optional: separater Upload-Endpoint für Bild-Upload nach Erstellung
    @PostMapping("/{id}/uploadBild")
    public ResponseEntity<Void> uploadBild(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        byte[] bildBytes = file.getBytes();
        boolean success = tastaturService.saveBild(id, bildBytes);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
