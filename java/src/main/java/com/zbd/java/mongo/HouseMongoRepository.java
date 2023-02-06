package com.zbd.java.mongo;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface HouseMongoRepository extends MongoRepository<HouseMongo, String> {

    List<HouseMongo> findByLocationLocCity(String city);

    List<HouseMongo> findByLocationLocCityOrderByPrice(String locCity);

    @Aggregation("{ $group: { _id: '$location.locCity', avgPrice: { $avg: '$price' } } }")
    List<AvgPriceByCity> getAvgPriceByCityMongo();
}

