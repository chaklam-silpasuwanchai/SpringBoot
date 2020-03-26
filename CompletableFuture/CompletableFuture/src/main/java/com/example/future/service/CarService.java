package com.example.future.service;

/* Credits: github.com/swathisprasad/spring-boot-completable-future */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.future.model.Car;
import com.example.future.repo.CarRepository;

@Service
public class CarService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
	
	@Autowired
    private CarRepository carRepository;
	
	@Async
    public CompletableFuture<List<Car>> saveCars(final InputStream inputStream) throws Exception {
        final long start = System.currentTimeMillis();
        List<Car> cars = parseCSVFile(inputStream);
        LOGGER.info("Saving a list of cars of size {} records", cars.size());
        cars = carRepository.saveAll(cars);
        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }
	
	//accepts a multipart file, parses it and stores the data in database
	private List<Car> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<Car> cars=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(";");
                    final Car car=new Car();
                    car.setManufacturer(data[0]);
                    car.setModel(data[1]);
                    car.setType(data[2]);
                    cars.add(car);
                }
                return cars;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
	
	//reads the data from the database
	@Async
    public CompletableFuture<List<Car>> getAllCars() throws InterruptedException {
        LOGGER.info("Request to get a list of cars");
        final List<Car> cars = carRepository.findAll();
        Thread.sleep(10000L);
        return CompletableFuture.completedFuture(cars);
    }

}
