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

    @PostMapping("/postgres")
    public Home createHome(@RequestBody Home home) {
        return homeService.createHome(home);
    }

    @PostMapping("/mongo")
    public HomeMongo createPost(@RequestBody HomeMongo homeMongo) {
        return homeService.createHomeMongo(homeMongo);
    }

//    @GetMapping("/mongo")
//    public List<HomeMongo> getHomeMongo() {
//        return homeService.getHomeMongo();
//    }

    @PostMapping("/mongo/json/{uploadLines}")
    public void uploadToMongoJSON(@PathVariable("uploadLines") Integer uploadLines) throws IOException {
        homeService.uploadToMongoJSON(uploadLines);
    }

    @PostMapping("/postgres/json/{uploadLines}")
    public void uploadItemsFromJSON2(@PathVariable("uploadLines") Integer uploadLines) throws IOException {
        homeService.uploadItemsFromJSON2(uploadLines);
    }

    @GetMapping("/mongo")
    public ResponseEntity<List<HomeMongo>> getHousesMongo() {
        return ResponseEntity.ok().body(homeService.findAllMongo());
    }

    @GetMapping("/postgres")
    public ResponseEntity<List<Home>> getHousesPostgres() {
        return ResponseEntity.ok().body(homeService.findAllPostgres());
    }

}