package com.example.demo.web;

import com.example.demo.persistence.KommentarEntität;
import com.example.demo.persistence.KommentarRepository;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.Kommentar;
import com.example.demo.web.api.KommentarCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tastaturen/{tastaturId}/comments")
@CrossOrigin(origins = "*")
public class KommentarRestController {

    private final KommentarRepository kommentarRepository;
    private final TastaturRepository tastaturRepository;

    public KommentarRestController(KommentarRepository kommentarRepository, TastaturRepository tastaturRepository) {
        this.kommentarRepository = kommentarRepository;
        this.tastaturRepository = tastaturRepository;
    }

    @GetMapping
    public ResponseEntity<List<Kommentar>> getKommentare(@PathVariable Long tastaturId) {
        var list = kommentarRepository.findByTastatur_IdOrderByCreatedAtDesc(tastaturId)
                .stream()
                .map(k -> new Kommentar(k.getId(), k.getName(), k.getText(), k.getCreatedAt()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> addKommentar(@PathVariable Long tastaturId, @RequestBody KommentarCreateRequest request) {
        var tastaturOpt = tastaturRepository.findById(tastaturId);
        if (tastaturOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        KommentarEntität kommentar = new KommentarEntität();
        kommentar.setName(request.getName());
        kommentar.setText(request.getText());
        kommentar.setCreatedAt(LocalDateTime.now());
        kommentar.setTastatur(tastaturOpt.get());

        kommentarRepository.save(kommentar);
        return ResponseEntity.ok().build();
    }
}

