package com.zbd.java;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/measure")
public class HouseControllerMeasure {

    private final HouseService houseService;
    private long startTime;
    private long endTime;

    public HouseControllerMeasure(HouseService houseService) {
        this.houseService = houseService;
    }

    public void prepareDatabase(int uploadLines) throws IOException {
        houseService.uploadToMongoJSON(uploadLines);
        houseService.uploadToPostgres(uploadLines);
    }

    public void cleanDatabase() {
        houseService.remove();
        houseService.removeMongo();
    }


    @GetMapping("")
    public ResponseEntity<Map<String, Long>> getAllHouses(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.findAllMongo();
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.findAllPostgres();
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }


    @GetMapping("/city")
    public ResponseEntity<Map<String, Long>> getHousesByCity(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.getHousesByCityMongo(city);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.getHousesByCity(city);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/cityAndSort")
    public ResponseEntity<Map<String, Long>> getByLocationLocCityOrderByPrice(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.HomeMongoByLocationLocCityOrderByPriceMongo(city);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.getByLocationLocCityOrderByPricePostgres(city);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Long>> uploadFromJSON(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.uploadToMongoJSON(uploadLines);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.uploadToPostgres(uploadLines);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }

    @DeleteMapping("/lines")
    public ResponseEntity<Map<String, Long>> removeFirstItems(@RequestParam("deleteLines") int deleteLines, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.removeFirstItemsMongo(deleteLines);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.removeFirstItems(deleteLines);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }

    @DeleteMapping("/city")
    public ResponseEntity<Map<String, Long>> removeByCity(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        startTime = System.currentTimeMillis();
        houseService.removeByCityMongo(city);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.removeByCity(city);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }


    @GetMapping("/avg-price-by-city")
    public ResponseEntity<Map<String, Long>> getAvgPriceByCity(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();

        startTime = System.currentTimeMillis();
        houseService.getAvgPriceByCityMongo();
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.getAvgPriceByCityPostgres();
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }

    @PutMapping("/updateAdDescription")
    public ResponseEntity<Map<String, Long>> updateAdDescription(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();

        startTime = System.currentTimeMillis();
        houseService.updateAdDescriptionMongo();
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.updateAdDescriptionPostgres();
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }

    @PutMapping("/updateAdDescriptionCity")
    public ResponseEntity<Map<String, Long>> updateAdDescriptionWhereCity(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();

        startTime = System.currentTimeMillis();
        houseService.updateAdDescriptionWhereCityMongo(city);
        endTime = System.currentTimeMillis();
        map.put("mongo", endTime - startTime);

        startTime = System.currentTimeMillis();
        houseService.updateAdDescriptionWhereCityPostgres(city);
        endTime = System.currentTimeMillis();
        map.put("postgres", endTime - startTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }
}
