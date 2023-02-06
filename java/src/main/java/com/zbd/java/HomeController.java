package com.zbd.java;

import com.zbd.java.mongo.HomeMongo;
import com.zbd.java.postgres.Home;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@Slf4j
@AllArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/mongo")
    public ResponseEntity<List<HomeMongo>> getAllHousesMongo() {
        return ResponseEntity.ok().body(homeService.findAllMongo());
    }

    @GetMapping("/postgres")
    public ResponseEntity<List<Home>> getAllHousesPostgres() {
        return ResponseEntity.ok().body(homeService.findAllPostgres());
    }

    @GetMapping("/postgres/city")
    public List<Home> getHousesByCityPostgres(@RequestParam("city") String city) {
        return homeService.getHousesByCity(city);
    }

    @GetMapping("/mongo/city")
    public List<HomeMongo> getHousesByCityMongo(@RequestParam("city") String city) {
        return homeService.getHousesByCityMongo(city);
    }

    @GetMapping("/postgres/cityAndSort")
    public List<Home> getByLocationLocCityOrderByPricePostgres(@RequestParam("city") String city) {
        return homeService.getByLocationLocCityOrderByPricePostgres(city);
    }

    @GetMapping("/mongo/cityAndSort")
    public List<HomeMongo> HomeMongoByLocationLocCityOrderByPriceMongo(@RequestParam("city") String city) {
        return homeService.HomeMongoByLocationLocCityOrderByPriceMongo(city);
    }

    @PostMapping("/postgres")
    public Home createHome(@RequestBody Home home) {
        return homeService.createHome(home);
    }

    @PostMapping("/mongo")
    public HomeMongo createPost(@RequestBody HomeMongo homeMongo) {
        return homeService.createHomeMongo(homeMongo);
    }

    @PostMapping("/mongo/json")
    public void uploadToMongoJSON(@RequestParam("uploadLines") int uploadLines) throws IOException {
        homeService.uploadToMongoJSON(uploadLines);
    }

    @PostMapping("/postgres/json")
    public void uploadToPostgres(@RequestParam("uploadLines") int uploadLines) throws IOException {
        homeService.uploadToPostgres(uploadLines);
    }

    @DeleteMapping("/postgres/lines")
    public void removeFirstItems(@RequestParam("deleteLines") int deleteLines) {
        homeService.removeFirstItems(deleteLines);
    }

    @DeleteMapping("/mongo/lines")
    public void removeFirstItemsMongo(@RequestParam("deleteLines") int deleteLines) {
        homeService.removeFirstItemsMongo(deleteLines);
    }

    @DeleteMapping("/postgres/city")
    public void removeByCity(@RequestParam("city") String city) {
        homeService.removeByCity(city);
    }

    @DeleteMapping("/mongo/city")
    public void removeByCityItemsMongo(@RequestParam("city") String city) {
        homeService.removeByCityMongo(city);
    }


    @DeleteMapping("/postgres")
    public void removeByCity() {
        homeService.remove();
    }

    @DeleteMapping("/mongo")
    public void removeMongo() {
        homeService.removeMongo();
    }


}
