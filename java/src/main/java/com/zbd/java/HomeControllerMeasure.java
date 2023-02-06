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
public class HomeControllerMeasure {

    private final HomeService homeService;

    public void prepareDatabase(int uploadLines) throws IOException {
        homeService.uploadToMongoJSON(uploadLines);
        homeService.uploadToPostgres(uploadLines);

    }

    public void cleanDatabase() {
        homeService.remove();
        homeService.removeMongo();
    }


    @GetMapping("")
    public ResponseEntity<Map<String, Long>> getAllHouses(@RequestParam("uploadLines") int uploadLines) throws IOException {
        prepareDatabase(uploadLines);
        HashMap<String, Long> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        homeService.findAllMongo();
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.findAllPostgres();
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
        homeService.getHousesByCityMongo(city);
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.getHousesByCity(city);
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
        homeService.HomeMongoByLocationLocCityOrderByPriceMongo(city);
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.getByLocationLocCityOrderByPricePostgres(city);
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
        homeService.uploadToMongoJSON(uploadLines);
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.uploadToPostgres(uploadLines);
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
        homeService.removeFirstItemsMongo(deleteLines);
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.removeFirstItems(deleteLines);
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
        homeService.removeByCityMongo(city);
        long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        map.put("mongo", elapsedTime);

        startTime = System.currentTimeMillis();
        homeService.removeByCity(city);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        map.put("postgres", elapsedTime);

        cleanDatabase();
        return ResponseEntity.ok().body(map);

    }
}
