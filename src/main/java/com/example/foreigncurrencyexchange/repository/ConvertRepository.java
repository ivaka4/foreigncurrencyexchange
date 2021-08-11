package com.example.foreigncurrencyexchange.repository;

import com.example.foreigncurrencyexchange.repository.entity.ConvertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertRepository extends JpaRepository<ConvertEntity, String> {

}
