package com.example.demo.persistence;


import com.example.demo.web.api.Tastatur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TastaturRepository extends JpaRepository<TastaturEntität, Long> {

List<TastaturEntität> findAllByTastaturname (String tastaturName);
}
