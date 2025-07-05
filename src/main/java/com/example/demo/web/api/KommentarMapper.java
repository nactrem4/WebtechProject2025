package com.example.demo.web.api;


import com.example.demo.persistence.KommentarEntität;

public class KommentarMapper {

    public static Kommentar toApi(KommentarEntität entity) {
        if (entity == null) return null;

        return new Kommentar(
                entity.getId(),
                entity.getName(),
                entity.getText(),
                entity.getCreatedAt()
        );
    }
}
