package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KommentarRepository extends JpaRepository<KommentarEntität, Long> {
    List<KommentarEntität> findByTastatur_IdOrderByCreatedAtDesc(Long tastaturId);
}