package com.zbd.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbd.java.mongo.AvgPriceByCity;
import com.zbd.java.mongo.HouseMongo;
import com.zbd.java.mongo.HouseMongoRepository;
import com.zbd.java.postgres.HousePostgres;
import com.zbd.java.postgres.HousePostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    private final HousePostgresRepository housePostgresRepository;
    private final HouseMongoRepository houseMongoRepository;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    public HouseService(HousePostgresRepository housePostgresRepository, HouseMongoRepository houseMongoRepository) {
        this.housePostgresRepository = housePostgresRepository;
        this.houseMongoRepository = houseMongoRepository;
    }


    public HousePostgres createHome(HousePostgres housePostgres) {
        return housePostgresRepository.save(housePostgres);
    }

    public HouseMongo createHomeMongo(HouseMongo houseMongo) {
        return houseMongoRepository.save(houseMongo);
    }

    public BufferedReader getJSON() throws FileNotFoundException {
        String path = "C:\\Users\\Kamil K\\Documents\\repo\\ZBD\\python\\houses.json";
        String filePath = path.replace("\\", "/");
        return new BufferedReader(new FileReader(filePath));
    }

    public void uploadToMongoJSON(int uploadLines) throws IOException {
        List<HouseMongo> items = new ArrayList<>();
        String line;
        int count = 0;
        BufferedReader br = getJSON();
        while ((line = br.readLine()) != null && count < uploadLines) {
            HouseMongo item = MAPPER.readValue(line, HouseMongo.class);
            items.add(item);
            count++;
        }
        br.close();
        houseMongoRepository.saveAll(items);
    }

    public void uploadToPostgres(int uploadLines) throws IOException {
        List<HousePostgres> items = new ArrayList<>();
        BufferedReader br = getJSON();
        String line;
        int count = 0;
        while ((line = br.readLine()) != null && count < uploadLines) {
            HousePostgres item = MAPPER.readValue(line, HousePostgres.class);
            items.add(item);
            count++;
        }
        br.close();
        housePostgresRepository.saveAll(items);
    }


    public void removeFirstItems(int number) {
        List<HousePostgres> items = housePostgresRepository.findAll();
        int count = 0;
        for (HousePostgres item : items) {
            housePostgresRepository.delete(item);
            count++;
            if (count >= number) {
                break;
            }
        }
    }

    public void removeFirstItemsMongo(int number) {
        List<HouseMongo> items = houseMongoRepository.findAll();
        int count = 0;
        for (HouseMongo item : items) {
            houseMongoRepository.delete(item);
            count++;
            if (count >= number) {
                break;
            }
        }
    }


    public void remove() {
        List<HousePostgres> items = housePostgresRepository.findAll();
        housePostgresRepository.deleteAll(items);
    }

    public void removeMongo() {
        List<HouseMongo> items = houseMongoRepository.findAll();
        houseMongoRepository.deleteAll(items);
    }

    public void removeByCity(String city) {
        List<HousePostgres> items = housePostgresRepository.findByLocationLocCity(city);
        housePostgresRepository.deleteAll(items);
    }

    public void removeByCityMongo(String city) {
        List<HouseMongo> items = houseMongoRepository.findByLocationLocCity(city);
        houseMongoRepository.deleteAll(items);
    }

    public List<HousePostgres> findAllPostgres() {
        return housePostgresRepository.findAll();
    }

    public List<HouseMongo> findAllMongo() {
        return houseMongoRepository.findAll();
    }

    public List<HousePostgres> getHousesByCity(String city) {
        return housePostgresRepository.findByLocationLocCity(city);
    }

    public List<HouseMongo> getHousesByCityMongo(String city) {
        return houseMongoRepository.findByLocationLocCity(city);
    }

    public List<HousePostgres> getByLocationLocCityOrderByPricePostgres(String city) {
        return housePostgresRepository.findByLocationLocCityOrderByPrice(city);
    }

    public List<HouseMongo> HomeMongoByLocationLocCityOrderByPriceMongo(String city) {
        return houseMongoRepository.findByLocationLocCityOrderByPrice(city);
    }

    public List<AvgPriceByCity> getAvgPriceByCityMongo() {
        return houseMongoRepository.getAvgPriceByCityMongo();
    }

    public List<Object[]> getAvgPriceByCityPostgres() {
        return housePostgresRepository.getAvgPriceByCityPostgres();
    }

}
