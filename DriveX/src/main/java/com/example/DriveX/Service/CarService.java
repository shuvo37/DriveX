package com.example.DriveX.Service;
import com.example.DriveX.DTO.AddCar;
import com.example.DriveX.DTO.UpdateCar;
import com.example.DriveX.DTO.UpdateCarActiveStatus;
import com.example.DriveX.DTO.UpdateCarRentalStatus;
import com.example.DriveX.Enums.RentalStatus;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.Company;
import com.example.DriveX.Repository.CarRepository;
import com.example.DriveX.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Car addCar(AddCar carInfo) {


         if(carRepository.existsByModelName(carInfo.getModelName())) {

             throw new RuntimeException("Car already exists");

         }

        Company company = companyRepository.findByCompanyName(carInfo.getCompanyName())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Car car = new Car();

        car.setCompany(company);
        car.setModelName(carInfo.getModelName());
        car.setPricePerHour(carInfo.getPricePerHour());
        car.setRentalStatus(carInfo.getRentalStatus());
        car.setSeats(carInfo.getSeats());
        car.setFuel(carInfo.getFuel());
        car.setImageUrl(carInfo.getImageUrl());
        car.setTag(carInfo.getTag());
        car.setIsActive(true);

       // System.out.println(" car fuel is " + car.getFuel());

        return carRepository.save(car);
    }

    public Car updateCar(UpdateCar carInfo) {


        Car car = carRepository.findByModelName(carInfo.getModelName())
                .orElseThrow(() -> new RuntimeException("Car not found"));



        if(!car.getPricePerHour().equals(carInfo.getPricePerHour())) {

              car.setPricePerHour(carInfo.getPricePerHour());
        }

        if(!car.getRentalStatus().equals(carInfo.getRentalStatus())) {

            car.setRentalStatus(carInfo.getRentalStatus());
        }

        if(!car.getSeats().equals(carInfo.getSeats())) {
            car.setSeats(carInfo.getSeats());
        }

        if(!car.getFuel().equals(carInfo.getFuel())) {
            car.setFuel(carInfo.getFuel());
        }

        if(!car.getImageUrl().equals(carInfo.getImageUrl())) {
            car.setImageUrl(carInfo.getImageUrl());
        }
        if(!car.getTag().equals(carInfo.getTag())) {
            car.setTag(carInfo.getTag());
        }

        carRepository.save(car);
        return car;

    }

    public Car UpdateCarRentalStatus(UpdateCarRentalStatus updateCarRentalStatus)
    {

        Car car = carRepository.findById(updateCarRentalStatus.getCarId())
                .orElseThrow(()-> new RuntimeException("Car not found"));

        car.setRentalStatus(updateCarRentalStatus.getRentalStatus());

        return carRepository.save(car);

    }

    public Car UpdateCarRentalStatus(Long carId)
    {

        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not found in "));

        car.setRentalStatus(RentalStatus.MAINTENANCE);

        return carRepository.save(car);

    }

    public List<Car> findAllCars() {

        List<Car> cars = carRepository.findAll();

       // System.out.println(" cars size " + cars.size());
        cars.removeIf(car -> !car.getCompany().getIsActive());
        cars.removeIf(car -> !car.getIsActive());

        return cars;
    }

   public Car UpdateCarActiveStatus(UpdateCarActiveStatus updateCarActiveStatus)
   {
        System.out.println(" ....................active-status: " + updateCarActiveStatus.toString());

       Car car = carRepository.findByModelName(updateCarActiveStatus.getModelName())
                .orElseThrow(() -> new RuntimeException("Car not found"));

       car.setIsActive(updateCarActiveStatus.getIsActive());

       return carRepository.save(car);

   }


   public  List<Car> getAllRentedCars(RentalStatus rentalStatus)
   {

        List<Car> cars = carRepository.findByRentalStatus(rentalStatus);

        return cars;

   }




}
