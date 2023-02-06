package com.zbd.java.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {

    List<Home> findByLocationLocCity(String locCity);

    List<Home> findByLocationLocCityOrderByPrice(String locCity);

}
