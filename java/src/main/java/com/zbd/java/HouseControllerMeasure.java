package com.zbd.java;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/measure")
@Slf4j
@AllArgsConstructor
public class HouseControllerMeasure {

    private final HouseService houseService;

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
        long startTime = System.currentTimeMillis();
        houseService.findAllMongo();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.findAllPostgres();
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }


    @GetMapping("/city")
    public ResponseEntity<Map<String, Long>> getHousesByCity(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.getHousesByCityMongo(city);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.getHousesByCity(city);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/cityAndSort")
    public ResponseEntity<Map<String, Long>> getByLocationLocCityOrderByPrice(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.HomeMongoByLocationLocCityOrderByPriceMongo(city);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.getByLocationLocCityOrderByPricePostgres(city);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Long>> uploadFromJSON(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.uploadToMongoJSON(uploadLines);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.uploadToPostgres(uploadLines);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }

    @DeleteMapping("/lines")
    public ResponseEntity<Map<String, Long>> removeFirstItems(@RequestParam("deleteLines") int deleteLines, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.removeFirstItemsMongo(deleteLines);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.removeFirstItems(deleteLines);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);
    }

    @DeleteMapping("/city")
    public ResponseEntity<Map<String, Long>> removeByCity(@RequestParam("city") String city, @RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.removeByCityMongo(city);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.removeByCity(city);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }


    @GetMapping("/avg-price-by-city")
    public ResponseEntity<Map<String, Long>> getAvgPriceByCity(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        houseService.getAvgPriceByCityMongo();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        houseService.getAvgPriceByCityPostgres();
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }
}
