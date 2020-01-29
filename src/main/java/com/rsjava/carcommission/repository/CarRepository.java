package com.rsjava.carcommission.repository;

import com.rsjava.carcommission.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

Optional<Car> findById(long id);

}
