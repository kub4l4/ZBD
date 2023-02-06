package com.zbd.java.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface HomeMongoRepository extends MongoRepository<HomeMongo, String> {

    List<HomeMongo> findByLocationLocCity(String city);

    List<HomeMongo> findByLocationLocCityOrderByPrice(String locCity);


}
