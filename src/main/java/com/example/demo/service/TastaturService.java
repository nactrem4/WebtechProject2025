package com.example.demo.service;

import com.example.demo.persistence.TastaturEntität;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TastaturService {

    private final TastaturRepository tastaturRepository;

    public TastaturService(TastaturRepository tastaturRepository) {
        this.tastaturRepository = tastaturRepository;
    }

    public List<TastaturEntität> findall() {
        return tastaturRepository.findAll();
    }

    public TastaturEntität findById(Long id) {
        return tastaturRepository.findById(id).orElse(null);
    }

    public TastaturEntität create(TastaturCreateRequest request) {
        TastaturEntität entity = new TastaturEntität();
        entity.setTastaturName(request.getTastaturName());
        entity.setModell(request.getModell());
        entity.setSwitches(request.getSwitches());
        entity.setKeycaps(request.getKeycaps());
        entity.setBeschreibung(request.getBeschreibung());

        if (request.getBild() != null && !request.getBild().isEmpty()) {
            byte[] bildBytes = Base64.getDecoder().decode(request.getBild());
            entity.setBild(bildBytes);
        }

        return tastaturRepository.save(entity);
    }

    public TastaturEntität update(Long id, TastaturCreateRequest request) {
        Optional<TastaturEntität> optEntity = tastaturRepository.findById(id);
        if (optEntity.isEmpty()) return null;

        TastaturEntität entity = optEntity.get();
        entity.setTastaturName(request.getTastaturName());
        entity.setModell(request.getModell());
        entity.setSwitches(request.getSwitches());
        entity.setKeycaps(request.getKeycaps());
        entity.setBeschreibung(request.getBeschreibung());

        if (request.getBild() != null && !request.getBild().isEmpty()) {
            byte[] bildBytes = Base64.getDecoder().decode(request.getBild());
            entity.setBild(bildBytes);
        }
        // Optional: wenn kein Bild im Request, Bild beibehalten

        return tastaturRepository.save(entity);
    }

    public boolean deleteById(Long id) {
        if (!tastaturRepository.existsById(id)) return false;
        tastaturRepository.deleteById(id);
        return true;
    }

    public boolean saveBild(Long id, byte[] bildBytes) {
        Optional<TastaturEntität> optTastatur = tastaturRepository.findById(id);
        if (optTastatur.isEmpty()) return false;

        TastaturEntität tastatur = optTastatur.get();
        tastatur.setBild(bildBytes);
        tastaturRepository.save(tastatur);
        return true;
    }

    public TastaturEntität save(TastaturEntität entity) {
        return tastaturRepository.save(entity);
    }

    public List<TastaturEntität> sucheNachName(String query) {
        return tastaturRepository.findByTastaturNameContainingIgnoreCase(query);
    }
}
