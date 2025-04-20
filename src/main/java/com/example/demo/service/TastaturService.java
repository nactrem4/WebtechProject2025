package com.example.demo.service;

import com.example.demo.persistence.TastaturEntität;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.stereotype.Service;

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

    public Tastatur create(TastaturCreateRequest request) {
        var tastaturEntity = new TastaturEntität(request.getTastaturName(), request.getKeycaps(), request.getModell(), request.getSwitches());
        tastaturRepository.save(tastaturEntity);
        return transformEntität(tastaturEntity);
    }

    private Tastatur transformEntität(TastaturEntität tastaturEntität) {
        return new Tastatur(
                tastaturEntität.getId(),
                tastaturEntität.getTastaturname(),
                tastaturEntität.getModell(),
                tastaturEntität.getSwitches(),
                tastaturEntität.getKeycaps()
        );
    }
}

