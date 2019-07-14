package com.restapi.sockproject.repository;

import com.restapi.sockproject.model.Cheap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CheapRepository extends JpaRepository<Cheap, Integer> {
    Page<Cheap> findAll(Pageable pageable);
    List<Cheap> findByRouteLike(String route, Pageable pageable);
}
