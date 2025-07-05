package com.example.demo.service;

import com.example.demo.persistence.KommentarEntität;
import com.example.demo.persistence.KommentarRepository;
import com.example.demo.persistence.TastaturEntität;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.KommentarCreateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KommentarService {

    private final KommentarRepository kommentarRepository;
    private final TastaturRepository tastaturRepository;

    public KommentarService(KommentarRepository kommentarRepository, TastaturRepository tastaturRepository) {
        this.kommentarRepository = kommentarRepository;
        this.tastaturRepository = tastaturRepository;
    }

    public KommentarEntität createKommentar(Long tastaturId, KommentarCreateRequest request) {
        TastaturEntität tastatur = tastaturRepository.findById(tastaturId)
                .orElseThrow(() -> new IllegalArgumentException("Tastatur nicht gefunden"));

        KommentarEntität kommentar = new KommentarEntität();
        kommentar.setTastatur(tastatur);
        kommentar.setName(request.getName());
        kommentar.setText(request.getText());
        kommentar.setCreatedAt(LocalDateTime.now());

        return kommentarRepository.save(kommentar);
    }

    public List<KommentarEntität> findByTastaturId(Long tastaturId) {
        return kommentarRepository.findByTastatur_IdOrderByCreatedAtDesc(tastaturId);
    }
}
