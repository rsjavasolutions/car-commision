package com.rsjava.carcommission.controller;

import com.rsjava.carcommission.model.Car;
import com.rsjava.carcommission.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("cars")
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @PostMapping("cars")
    public Car addCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    @DeleteMapping("cars/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable long id){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            carRepository.delete(optionalCar.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("cars/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car car){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            optionalCar.get().setBrand(car.getBrand());
            optionalCar.get().setModel(car.getModel());
            optionalCar.get().setPrice(car.getPrice());
            carRepository.save(optionalCar.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
