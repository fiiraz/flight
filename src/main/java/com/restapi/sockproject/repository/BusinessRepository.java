package com.restapi.sockproject.repository;

import com.restapi.sockproject.model.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
    Page<Business> findAll(Pageable pageable);
    List<Business> findByDepartureLike(String departure, Pageable pageable);

}
