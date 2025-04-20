package com.example.demo.web;


import com.example.demo.service.TastaturService;
import com.example.demo.web.api.Tastatur;
import com.example.demo.web.api.TastaturCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TastaturRestController {

    private final TastaturService tastaturService;

    public TastaturRestController(TastaturService tastaturService) {
        this.tastaturService = tastaturService;
    }


    @GetMapping(path = "/api/v1/tastaturen")
    public ResponseEntity<List<Tastatur>> fetchTastaturen() {
        return ResponseEntity.ok(tastaturService.findall());
    }
    @PostMapping(path = "api/v1/tasturen")
    public ResponseEntity<Void> createTastatur(@RequestBody TastaturCreateRequest request) throws URISyntaxException {
    var tastatur = tastaturService.create(request);
    URI uri = new URI("api/v1/tasturen/" + tastatur.getId());
    return ResponseEntity.created(uri).build();
    }

}
