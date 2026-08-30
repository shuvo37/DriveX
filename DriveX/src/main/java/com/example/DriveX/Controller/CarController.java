package com.example.DriveX.Controller;

import com.example.DriveX.DTO.*;
import com.example.DriveX.Enums.RentalStatus;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Repository.CarRepository;
import com.example.DriveX.Service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    CarRepository carRepository;

    @PostMapping
    public ResponseEntity<?>addCar(@Valid @RequestBody AddCar carInfo , BindingResult result) {

        Car car = carService.addCar(carInfo);

        AddCarResponse addCarResponse = new AddCarResponse("car added successfully" , car);

        return ResponseEntity.ok(addCarResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@Valid @RequestBody UpdateCar carInfo) {

        System.out.println("inside update car controller");

        Car car = carService.updateCar(carInfo);

        UpdateCarResponse updateCarResponse = new UpdateCarResponse("car updated successfully", car);

        return ResponseEntity.ok(updateCarResponse);

    }


    @PatchMapping("/updateCarRentalStatus")
    public ResponseEntity<?> updateCarRentalStatus(@Valid @RequestBody UpdateCarRentalStatus updateCarRentalStatus) {

        Car car = carService.UpdateCarRentalStatus(updateCarRentalStatus);

        return ResponseEntity.ok(Map.of("message", "Car rental status updated successfully"));

    }

    @PatchMapping("/updateCarRentalStatus/{carId}")
    public ResponseEntity<?> updateCarRentalStatusSubmitted(@PathVariable Long carId) {

        Car car = carService.UpdateCarRentalStatus(carId);

        return ResponseEntity.ok(Map.of("message", "Car rental status updated successfully"));

    }

    @GetMapping
    public ResponseEntity<?> getCar() {

        List<Car> cars = carService.findAllCars();

        return  ResponseEntity.ok(cars);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars() {

        List<Car> cars = carRepository.findAll();

        cars.removeIf(car -> car.getRentalStatus().equals(RentalStatus.RENTED));
        cars.removeIf(car -> car.getRentalStatus().equals(RentalStatus.AVAILABLE));

        return  ResponseEntity.ok(cars);
    }

    @PatchMapping("/update-Active-Status")
    public ResponseEntity<?> updateCarStatus(@Valid @RequestBody UpdateCarActiveStatus updateCarActiveStatus) {

         Car car = carService.UpdateCarActiveStatus(updateCarActiveStatus);

         UpdateCarActiveStatusResponse updateCarActiveStatusResponse = new UpdateCarActiveStatusResponse("car updated successfully", car);

         return ResponseEntity.ok(updateCarActiveStatusResponse);
    }








}
