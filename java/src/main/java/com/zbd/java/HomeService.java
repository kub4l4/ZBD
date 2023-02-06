package com.zbd.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbd.java.mongo.HomeMongo;
import com.zbd.java.mongo.HomeMongoRepository;
import com.zbd.java.postgres.Home;
import com.zbd.java.postgres.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        String path = "C:\\Users\\Kamil K\\Documents\\repo\\ZBD\\python\\houses.json";
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

    public void uploadToPostgres(int uploadLines) throws IOException {
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


    public void removeFirstItems(int number) {
        List<Home> items = homeRepository.findAll();
        int count = 0;
        for (Home item : items) {
            homeRepository.delete(item);
            count++;
            if (count >= number) {
                break;
            }
        }
    }

    public void removeFirstItemsMongo(int number) {
        List<HomeMongo> items = homeMongoRepository.findAll();
        int count = 0;
        for (HomeMongo item : items) {
            homeMongoRepository.delete(item);
            count++;
            if (count >= number) {
                break;
            }
        }
    }


    public void remove() {
        List<Home> items = homeRepository.findAll();
        homeRepository.deleteAll(items);
    }

    public void removeMongo() {
        List<HomeMongo> items = homeMongoRepository.findAll();
        homeMongoRepository.deleteAll(items);
    }

    public void removeByCity(String city) {
        List<Home> items = homeRepository.findByLocationLocCity(city);
        homeRepository.deleteAll(items);
    }

    public void removeByCityMongo(String city) {
        List<HomeMongo> items = homeMongoRepository.findByLocationLocCity(city);
        homeMongoRepository.deleteAll(items);
    }

    public List<Home> findAllPostgres() {
        return homeRepository.findAll();
    }

    public List<HomeMongo> findAllMongo() {
        return homeMongoRepository.findAll();
    }

    public List<Home> getHousesByCity(String city) {
        return homeRepository.findByLocationLocCity(city);
    }

    public List<HomeMongo> getHousesByCityMongo(String city) {
        return homeMongoRepository.findByLocationLocCity(city);
    }

    public List<Home> getByLocationLocCityOrderByPricePostgres(String city) {
        return homeRepository.findByLocationLocCityOrderByPrice(city);
    }

    public List<HomeMongo> HomeMongoByLocationLocCityOrderByPriceMongo(String city) {
        return homeMongoRepository.findByLocationLocCityOrderByPrice(city);
    }

}
