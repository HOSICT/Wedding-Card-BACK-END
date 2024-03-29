package com.example.weddingCard.service;

import com.example.weddingCard.dto.CarDTO;
import com.example.weddingCard.entity.Car;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public void saveCar(CarDTO carDTO, Information information) {
        Car car = dtoCarEntity(carDTO, information);
        carRepository.save(car);
    }

    private Car dtoCarEntity(CarDTO carDTO, Information information) {
        List<Car> findWeddingIdCar = carRepository.findByWeddingId(information);
        Car car;
        if (findWeddingIdCar.isEmpty()) {
            car = new Car();
        } else {
            car = findWeddingIdCar.get(0);
        }

        car.setWeddingId(information);
        car.setCarMessage(carDTO.getCarMessage());

        return car;
    }

    public List<Car> findCarByWeddingId(List<Information> information) {
        return carRepository.findByWeddingIdIn(information);
    }
}
