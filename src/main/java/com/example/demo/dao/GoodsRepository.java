package com.example.demo.dao;

import com.example.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Optional<Goods> findById(Integer id);

}
