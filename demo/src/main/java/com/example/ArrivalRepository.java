package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, Long> {
    List<Arrival> findByAirport_Code(String airportCode);
}

