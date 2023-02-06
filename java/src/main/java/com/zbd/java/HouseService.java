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

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HousePostgresRepository housePostgresRepository;
    private final HouseMongoRepository houseMongoRepository;

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


    public void updateAdDescriptionMongo() {
        List<HouseMongo> houses = houseMongoRepository.findAll();
        for (HouseMongo house : houses) {
            house.setAdDescription("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit nisl elit, id commodo ex pellentesque mattis. Donec eleifend lectus et eros tincidunt, sit amet iaculis massa aliquet. Integer sodales dolor nec nunc bibendum finibus. Fusce aliquet, enim at tincidunt imperdiet, augue libero accumsan leo, congue bibendum risus leo a enim. Suspendisse facilisis tellus tortor, non rhoncus turpis venenatis nec. Aliquam nec interdum turpis. Curabitur pulvinar lacus ligula, quis posuere nulla sagittis nec. Integer nibh magna, condimentum sed consectetur nec, fringilla id lacus. Suspendisse potenti. Nam a tincidunt libero, sit amet iaculis mi. Proin libero elit, aliquet eget enim ut, mollis tempus mauris. In non libero magna. In porttitor nulla quam, eget pharetra enim varius eu. Maecenas volutpat ex orci, quis hendrerit sem mattis ut. Proin orci nibh, pharetra ut hendrerit hendrerit, cursus vel justo. In placerat tellus et pharetra tristique.

                Phasellus tincidunt id leo id varius. Vivamus iaculis quis lacus sit amet tempor. Donec dictum, massa ut venenatis scelerisque, ex magna ullamcorper ante, in aliquet est velit consequat urna. Duis laoreet augue risus, placerat imperdiet dolor pharetra eget. Sed scelerisque felis et massa iaculis hendrerit. Sed nec molestie est. Sed efficitur est a nibh convallis feugiat. Aenean et nisl efficitur, bibendum ex in, dictum leo. Integer urna nunc, lacinia at pulvinar quis, aliquet sed neque. Cras est odio, rutrum sit amet ante sit amet, viverra pulvinar neque. Integer semper, nisl in viverra sagittis, dolor leo tempor sem, non porta ex nunc eget urna.""");
            houseMongoRepository.save(house);
        }
    }

    public void updateAdDescriptionPostgres() {
        List<HousePostgres> houses = housePostgresRepository.findAll();
        for (HousePostgres house : houses) {
            house.setAdDescription("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit nisl elit, id commodo ex pellentesque mattis. Donec eleifend lectus et eros tincidunt, sit amet iaculis massa aliquet. Integer sodales dolor nec nunc bibendum finibus. Fusce aliquet, enim at tincidunt imperdiet, augue libero accumsan leo, congue bibendum risus leo a enim. Suspendisse facilisis tellus tortor, non rhoncus turpis venenatis nec. Aliquam nec interdum turpis. Curabitur pulvinar lacus ligula, quis posuere nulla sagittis nec. Integer nibh magna, condimentum sed consectetur nec, fringilla id lacus. Suspendisse potenti. Nam a tincidunt libero, sit amet iaculis mi. Proin libero elit, aliquet eget enim ut, mollis tempus mauris. In non libero magna. In porttitor nulla quam, eget pharetra enim varius eu. Maecenas volutpat ex orci, quis hendrerit sem mattis ut. Proin orci nibh, pharetra ut hendrerit hendrerit, cursus vel justo. In placerat tellus et pharetra tristique.

                Phasellus tincidunt id leo id varius. Vivamus iaculis quis lacus sit amet tempor. Donec dictum, massa ut venenatis scelerisque, ex magna ullamcorper ante, in aliquet est velit consequat urna. Duis laoreet augue risus, placerat imperdiet dolor pharetra eget. Sed scelerisque felis et massa iaculis hendrerit. Sed nec molestie est. Sed efficitur est a nibh convallis feugiat. Aenean et nisl efficitur, bibendum ex in, dictum leo. Integer urna nunc, lacinia at pulvinar quis, aliquet sed neque. Cras est odio, rutrum sit amet ante sit amet, viverra pulvinar neque. Integer semper, nisl in viverra sagittis, dolor leo tempor sem, non porta ex nunc eget urna.""");
            housePostgresRepository.save(house);
        }
    }

    public void updateAdDescriptionWhereCityMongo(String city) {
        List<HouseMongo> houses = houseMongoRepository.findByLocationLocCity(city);
        for (HouseMongo house : houses) {
            house.setAdDescription("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit nisl elit, id commodo ex pellentesque mattis. Donec eleifend lectus et eros tincidunt, sit amet iaculis massa aliquet. Integer sodales dolor nec nunc bibendum finibus. Fusce aliquet, enim at tincidunt imperdiet, augue libero accumsan leo, congue bibendum risus leo a enim. Suspendisse facilisis tellus tortor, non rhoncus turpis venenatis nec. Aliquam nec interdum turpis. Curabitur pulvinar lacus ligula, quis posuere nulla sagittis nec. Integer nibh magna, condimentum sed consectetur nec, fringilla id lacus. Suspendisse potenti. Nam a tincidunt libero, sit amet iaculis mi. Proin libero elit, aliquet eget enim ut, mollis tempus mauris. In non libero magna. In porttitor nulla quam, eget pharetra enim varius eu. Maecenas volutpat ex orci, quis hendrerit sem mattis ut. Proin orci nibh, pharetra ut hendrerit hendrerit, cursus vel justo. In placerat tellus et pharetra tristique.

                Phasellus tincidunt id leo id varius. Vivamus iaculis quis lacus sit amet tempor. Donec dictum, massa ut venenatis scelerisque, ex magna ullamcorper ante, in aliquet est velit consequat urna. Duis laoreet augue risus, placerat imperdiet dolor pharetra eget. Sed scelerisque felis et massa iaculis hendrerit. Sed nec molestie est. Sed efficitur est a nibh convallis feugiat. Aenean et nisl efficitur, bibendum ex in, dictum leo. Integer urna nunc, lacinia at pulvinar quis, aliquet sed neque. Cras est odio, rutrum sit amet ante sit amet, viverra pulvinar neque. Integer semper, nisl in viverra sagittis, dolor leo tempor sem, non porta ex nunc eget urna.""");
            houseMongoRepository.save(house);
        }
    }

    public void updateAdDescriptionWhereCityPostgres(String city) {
        List<HousePostgres> houses = housePostgresRepository.findByLocationLocCity(city);
        for (HousePostgres house : houses) {
            house.setAdDescription("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit nisl elit, id commodo ex pellentesque mattis. Donec eleifend lectus et eros tincidunt, sit amet iaculis massa aliquet. Integer sodales dolor nec nunc bibendum finibus. Fusce aliquet, enim at tincidunt imperdiet, augue libero accumsan leo, congue bibendum risus leo a enim. Suspendisse facilisis tellus tortor, non rhoncus turpis venenatis nec. Aliquam nec interdum turpis. Curabitur pulvinar lacus ligula, quis posuere nulla sagittis nec. Integer nibh magna, condimentum sed consectetur nec, fringilla id lacus. Suspendisse potenti. Nam a tincidunt libero, sit amet iaculis mi. Proin libero elit, aliquet eget enim ut, mollis tempus mauris. In non libero magna. In porttitor nulla quam, eget pharetra enim varius eu. Maecenas volutpat ex orci, quis hendrerit sem mattis ut. Proin orci nibh, pharetra ut hendrerit hendrerit, cursus vel justo. In placerat tellus et pharetra tristique.

                Phasellus tincidunt id leo id varius. Vivamus iaculis quis lacus sit amet tempor. Donec dictum, massa ut venenatis scelerisque, ex magna ullamcorper ante, in aliquet est velit consequat urna. Duis laoreet augue risus, placerat imperdiet dolor pharetra eget. Sed scelerisque felis et massa iaculis hendrerit. Sed nec molestie est. Sed efficitur est a nibh convallis feugiat. Aenean et nisl efficitur, bibendum ex in, dictum leo. Integer urna nunc, lacinia at pulvinar quis, aliquet sed neque. Cras est odio, rutrum sit amet ante sit amet, viverra pulvinar neque. Integer semper, nisl in viverra sagittis, dolor leo tempor sem, non porta ex nunc eget urna.""");
            housePostgresRepository.save(house);
        }
    }


}
