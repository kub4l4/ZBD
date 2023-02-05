package com.zbd.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbd.java.mongo.HomeMongo;
import com.zbd.java.mongo.HomeMongoRepository;
import com.zbd.java.postgres.Home;
import com.zbd.java.postgres.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final HomeMongoRepository homeMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    public HomeService(HomeRepository homeRepository, HomeMongoRepository homeMongoRepository) {
        this.homeRepository = homeRepository;
        this.homeMongoRepository = homeMongoRepository;
    }


    public Home createHome(Home home) {
        return homeRepository.save(home);
    }

    public HomeMongo createHomeMongo(HomeMongo homeMongo) {
        return homeMongoRepository.save(homeMongo);
    }

    public List<HomeMongo> getHomeMongo() {
        return homeMongoRepository.findAll();
    }

    public BufferedReader getJSON() throws FileNotFoundException {
    String path = "C:\\Users\\Kamil K\\PycharmProjects\\ZBD\\testfile.json";
    String filePath = path.replace("\\", "/");
    return new BufferedReader(new FileReader(filePath));
    }

    public void uploadToMongoJSON(int uploadLines) throws IOException {
        List<HomeMongo> items = new ArrayList<>();
        String line;
        int count = 0;
        BufferedReader br = getJSON();
        while ((line = br.readLine()) != null && count < uploadLines) {
            HomeMongo item = MAPPER.readValue(line, HomeMongo.class);
            items.add(item);
            count++;
        }
        br.close();
        homeMongoRepository.saveAll(items);
    }

    public void uploadItemsFromJSON2(int uploadLines) throws IOException {
        List<Home> items = new ArrayList<>();
        BufferedReader br = getJSON();
        String line;
        int count = 0;
        while ((line = br.readLine()) != null && count < uploadLines) {
            Home item = MAPPER.readValue(line, Home.class);
            items.add(item);
            count++;
        }
        br.close();
        homeRepository.saveAll(items);
    }


    public List<Home> findAllPostgres() {
        return homeRepository.findAll();
    }

    public List<HomeMongo> findAllMongo() {
        return homeMongoRepository.findAll();
    }


}
