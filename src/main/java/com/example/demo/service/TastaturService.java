package com.example.demo.service;

import com.example.demo.persistence.TastaturEntität;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TastaturService {

    private final TastaturRepository tastaturRepository;

    public TastaturService(TastaturRepository tastaturRepository) {
        this.tastaturRepository = tastaturRepository;
    }

    public List<Tastatur> findall() {
        List<TastaturEntität> tastaturen = tastaturRepository.findAll();
        return tastaturen.stream()
                .map(this::transformEntität)
                .collect(Collectors.toList());
    }

    public Tastatur findById(Long id) {
        var tastaturEntität = tastaturRepository.findById(id);
        return tastaturEntität.map(this::transformEntität).orElse(null);
    }

    public Tastatur create(TastaturCreateRequest request) {
        byte[] bild = null;
        if (request.getBildBase64() != null && !request.getBildBase64().isEmpty()) {
            bild = Base64.getDecoder().decode(request.getBildBase64());
        }
        var tastaturEntität = new TastaturEntität(
                request.getTastaturName(),
                request.getModell(),
                request.getSwitches(),
                request.getKeycaps(),
                request.getBeschreibung(),
                request.getBildUrl(),
                bild
        );
        tastaturEntität = tastaturRepository.save(tastaturEntität);
        return transformEntität(tastaturEntität);
    }

    public Tastatur update(Long id, TastaturCreateRequest request) {
        var tastaturEntitätOptional = tastaturRepository.findById(id);
        if (tastaturEntitätOptional.isEmpty()) {
            return null;
        }
        var tastaturEntität = tastaturEntitätOptional.get();
        tastaturEntität.setTastaturName(request.getTastaturName());
        tastaturEntität.setModell(request.getModell());
        tastaturEntität.setSwitches(request.getSwitches());
        tastaturEntität.setKeycaps(request.getKeycaps());
        tastaturEntität.setBeschreibung(request.getBeschreibung());
        tastaturEntität.setBildUrl(request.getBildUrl());

        if (request.getBildBase64() != null && !request.getBildBase64().isEmpty()) {
            byte[] bild = Base64.getDecoder().decode(request.getBildBase64());
            tastaturEntität.setBild(bild);
        }

        tastaturEntität = tastaturRepository.save(tastaturEntität);
        return transformEntität(tastaturEntität);
    }

    public boolean deleteById(Long id) {
        if (!tastaturRepository.existsById(id)) {
            return false;
        }
        tastaturRepository.deleteById(id);
        return true;
    }

    public boolean saveBild(Long id, byte[] bild) {
        var opt = tastaturRepository.findById(id);
        if (opt.isEmpty()) return false;
        var tastatur = opt.get();
        tastatur.setBild(bild);
        tastaturRepository.save(tastatur);
        return true;
    }

    private Tastatur transformEntität(TastaturEntität tastaturEntität) {
        return new Tastatur(
                tastaturEntität.getId(),
                tastaturEntität.getTastaturName(),
                tastaturEntität.getModell(),
                tastaturEntität.getSwitches(),
                tastaturEntität.getKeycaps(),
                tastaturEntität.getBeschreibung(),
                tastaturEntität.getBildUrl(),
                tastaturEntität.getBild()
        );
    }
}
