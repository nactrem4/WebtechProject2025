package com.example.demo.web;

import com.example.demo.persistence.TastaturEntität;
import com.example.demo.service.TastaturService;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tastaturen")
@CrossOrigin(origins = "*")
public class TastaturRestController {

    private final TastaturService tastaturService;

    public TastaturRestController(TastaturService tastaturService) {
        this.tastaturService = tastaturService;
    }

    @GetMapping
    public ResponseEntity<List<TastaturEntität>> fetchTastaturen(@RequestParam(required = false, name = "q") String query) {
        List<TastaturEntität> ergebnisse;
        if (query == null || query.isBlank()) {
            ergebnisse = tastaturService.findall();
        } else {
            ergebnisse = tastaturService.sucheNachName(query);  // Teil-Suche verwenden
        }

        if (ergebnisse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ergebnisse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TastaturEntität> fetchTastaturById(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        return tastatur != null ? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TastaturEntität> createTastatur(@RequestBody TastaturCreateRequest request) {
        var tastatur = tastaturService.create(request);
        return ResponseEntity.created(URI.create("/tastaturen/" + tastatur.getId()))
                .body(tastatur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TastaturEntität> updateTastatur(@PathVariable Long id, @RequestBody TastaturCreateRequest request) {
        var tastatur = tastaturService.update(id, request);
        return tastatur != null ? ResponseEntity.ok(tastatur) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTastatur(@PathVariable Long id) {
        boolean success = tastaturService.deleteById(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/bild")
    public ResponseEntity<byte[]> getTastaturBild(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        if (tastatur == null || tastatur.getBild() == null || tastatur.getBild().length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(tastatur.getBild());
    }

    @PostMapping("/{id}/uploadBild")
    public ResponseEntity<Void> uploadBild(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) return ResponseEntity.badRequest().build();
        boolean success = tastaturService.saveBild(id, file.getBytes());
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/bild")
    public ResponseEntity<Void> deleteBild(@PathVariable Long id) {
        var tastatur = tastaturService.findById(id);
        if (tastatur == null) {
            return ResponseEntity.notFound().build();
        }
        tastatur.setBild(null);
        tastaturService.save(tastatur);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<TastaturEntität> vote(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String type = body.get("type");
        if (!"up".equals(type) && !"down".equals(type)) {
            return ResponseEntity.badRequest().build();
        }
        var tastatur = tastaturService.findById(id);
        if (tastatur == null) {
            return ResponseEntity.notFound().build();
        }
        if ("up".equals(type)) {
            tastatur.setUpvotes(tastatur.getUpvotes() + 1);
        } else {
            tastatur.setDownvotes(tastatur.getDownvotes() + 1);
        }
        TastaturEntität updatedTastatur = tastaturService.save(tastatur);
        return ResponseEntity.ok(updatedTastatur);
    }
}
