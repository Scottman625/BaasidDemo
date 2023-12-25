package com.example.demo.service;

import com.example.demo.dao.GoodsRepository;
import com.example.demo.dto.GoodsDTO;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public GoodsDTO retrieveGoods(Integer goodsId) {
        Goods good = goodsRepository.findById(goodsId).orElseThrow(()-> new NotFoundException(goodsId.toString()));
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setId(good.getId());
        goodsDTO.setGoods_name(good.getGoods_name());
        return goodsDTO;
    }

    public GoodsDTO createGoods(User user, String name) {
        Goods good = new Goods();
        good.setGoods_name(name);
        good.setCrUser(user.get_id());
        good.setUpUser(user.get_id());
        good.setCrDatetime(LocalDateTime.now());
        good.setUpDatetime(LocalDateTime.now());
        goodsRepository.save(good);
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setId(good.getId());
        goodsDTO.setGoods_name(good.getGoods_name());
        return goodsDTO;
    }


    public GoodsDTO updateGoods(User user, String goodsName,Integer goodsId) {
        Goods good = goodsRepository.findById(goodsId).orElseThrow(()-> new NotFoundException(goodsId.toString()));
        good.setGoods_name(goodsName);
        good.setUpUser(user.get_id());
        good.setUpDatetime(LocalDateTime.now());
        goodsRepository.save(good);
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setId(good.getId());
        goodsDTO.setGoods_name(good.getGoods_name());
        return goodsDTO;
    }

    public void deleteGoods(Integer goodsId) {
        goodsRepository.deleteById(goodsId);
    }
}
