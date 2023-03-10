package com.zbd.java.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HousePostgresRepository extends JpaRepository<HousePostgres, Long> {

    List<HousePostgres> findByLocationLocCity(String locCity);

    List<HousePostgres> findByLocationLocCityOrderByPrice(String locCity);

    @Query("SELECT h.location.locCity, AVG(h.price) FROM HousePostgres h GROUP BY h.location.locCity")
    List<Object[]> getAvgPriceByCityPostgres();
}
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public void updateAdDescription() {
//        Query query = new Query();
//        query.limit(100);
//        Update update = new Update();
//        update.set("adDescription", "Lorem Impusm");
//        mongoTemplate.updateMulti(query, update, "property");
//    }
