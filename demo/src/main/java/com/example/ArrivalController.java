package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/arrivals")
public class ArrivalController {
    @Autowired
    private ArrivalRepository arrivalRepository;

    @GetMapping
    @Transactional
    public List<Arrival> getArrivals(@RequestParam(required = false) String airportCode) {
        if (airportCode != null && !airportCode.isEmpty()) {
            return arrivalRepository.findByAirport_Code(airportCode);
        } else {
            return arrivalRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    public Arrival getArrivalById(@PathVariable Long id) {
        return arrivalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arrival not found"));
    }

    @PostMapping
    public Arrival createArrival(@RequestBody Arrival arrival) {
        return arrivalRepository.save(arrival);
    }

    @PutMapping("/{id}")
    public Arrival updateArrival(@PathVariable Long id, @RequestBody Arrival arrival) {
        Arrival existingArrival = arrivalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arrival not found"));
        existingArrival.setFlightNumber(arrival.getFlightNumber());
        existingArrival.setScheduledArrivalTime(arrival.getScheduledArrivalTime());
        existingArrival.setActualArrivalTime(arrival.getActualArrivalTime());
        existingArrival.setStatus(arrival.getStatus());
        existingArrival.setAircraft(arrival.getAircraft());
        existingArrival.setAirline(arrival.getAirline());
        existingArrival.setAirport(arrival.getAirport());
        existingArrival.setGate(arrival.getGate());
        return arrivalRepository.save(existingArrival);
    }

    @DeleteMapping("/{id}")
    public void deleteArrival(@PathVariable Long id) {
        arrivalRepository.deleteById(id);
    }
}
