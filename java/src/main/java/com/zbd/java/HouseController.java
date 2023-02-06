package com.zbd.java;

import com.zbd.java.mongo.AvgPriceByCity;
import com.zbd.java.mongo.HouseMongo;
import com.zbd.java.postgres.HousePostgres;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@AllArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/mongo")
    public ResponseEntity<List<HouseMongo>> getAllHousesMongo() {
        return ResponseEntity.ok().body(houseService.findAllMongo());
    }

    @GetMapping("/postgres")
    public ResponseEntity<List<HousePostgres>> getAllHousesPostgres() {
        return ResponseEntity.ok().body(houseService.findAllPostgres());
    }

    @GetMapping("/postgres/city")
    public List<HousePostgres> getHousesByCityPostgres(@RequestParam("city") String city) {
        return houseService.getHousesByCity(city);
    }

    @GetMapping("/mongo/city")
    public List<HouseMongo> getHousesByCityMongo(@RequestParam("city") String city) {
        return houseService.getHousesByCityMongo(city);
    }

    @GetMapping("/postgres/cityAndSort")
    public List<HousePostgres> getByLocationLocCityOrderByPricePostgres(@RequestParam("city") String city) {
        return houseService.getByLocationLocCityOrderByPricePostgres(city);
    }

    @GetMapping("/mongo/cityAndSort")
    public List<HouseMongo> HomeMongoByLocationLocCityOrderByPriceMongo(@RequestParam("city") String city) {
        return houseService.HomeMongoByLocationLocCityOrderByPriceMongo(city);
    }

    @PostMapping("/postgres")
    public HousePostgres createHome(@RequestBody HousePostgres housePostgres) {
        return houseService.createHome(housePostgres);
    }

    @PostMapping("/mongo")
    public HouseMongo createPost(@RequestBody HouseMongo houseMongo) {
        return houseService.createHomeMongo(houseMongo);
    }

    @PostMapping("/mongo/json")
    public void uploadToMongoJSON(@RequestParam("uploadLines") int uploadLines) throws IOException {
        houseService.uploadToMongoJSON(uploadLines);
    }

    @PostMapping("/postgres/json")
    public void uploadToPostgres(@RequestParam("uploadLines") int uploadLines) throws IOException {
        houseService.uploadToPostgres(uploadLines);
    }

    @DeleteMapping("/postgres/lines")
    public void removeFirstItems(@RequestParam("deleteLines") int deleteLines) {
        houseService.removeFirstItems(deleteLines);
    }

    @DeleteMapping("/mongo/lines")
    public void removeFirstItemsMongo(@RequestParam("deleteLines") int deleteLines) {
        houseService.removeFirstItemsMongo(deleteLines);
    }

    @DeleteMapping("/postgres/city")
    public void removeByCity(@RequestParam("city") String city) {
        houseService.removeByCity(city);
    }

    @DeleteMapping("/mongo/city")
    public void removeByCityItemsMongo(@RequestParam("city") String city) {
        houseService.removeByCityMongo(city);
    }


    @DeleteMapping("/postgres")
    public void removeByCity() {
        houseService.remove();
    }

    @DeleteMapping("/mongo")
    public void removeMongo() {
        houseService.removeMongo();
    }


    @GetMapping("/mongo/avg-price-by-city")
    public List<AvgPriceByCity> getAvgPriceByCityMongo() {
        return houseService.getAvgPriceByCityMongo();
    }

    @GetMapping("/postgres/avg-price-by-city")
    public List<Object[]> getAvgPriceByCityPostgres() {
        return houseService.getAvgPriceByCityPostgres();
    }

}
