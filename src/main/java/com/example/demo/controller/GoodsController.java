package com.example.demo.controller;

import com.example.demo.dao.GoodsRepository;
import com.example.demo.dto.GoodsDTO;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import com.example.demo.service.GoodsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping
    public ResponseEntity<?> getAllGoods(HttpServletRequest request){
        try{

            User user = jwtService.tokenGetUser(request);
            List<Goods> goods = goodsRepository.findAll();
            List<GoodsDTO> goodsDTOS = new ArrayList<>();
            for(Goods good: goods){
                GoodsDTO goodsDTO = new GoodsDTO();
                goodsDTO.setId(good.getId());
                goodsDTO.setGoods_name(good.getGoods_name());
                goodsDTOS.add(goodsDTO);
            }
            return ResponseEntity.ok(goodsDTOS);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("權限不足");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }

    }

    @GetMapping("/{goodsId}/")
    public ResponseEntity<?> retrieveGoods(
            @PathVariable Integer goodsId,
            HttpServletRequest request
    ) {
        try{
            User user = jwtService.tokenGetUser(request);
            GoodsDTO goodDTO = goodsService.retrieveGoods(goodsId);
            return ResponseEntity.ok(goodDTO);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("權限不足");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createGoods(@RequestParam String goods_name,HttpServletRequest request
    ) {
        try{
            User user = jwtService.tokenGetUser(request);
            GoodsDTO goodsDTO  = goodsService.createGoods(user,goods_name);
            return ResponseEntity.ok(goodsDTO);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("權限不足");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }
    }

    @PutMapping("/update/{goodsId}/")
    public ResponseEntity<?> updateGoods(@RequestParam String goods_name,HttpServletRequest request,@PathVariable Integer goodsId
    ){
        try{
            User user = jwtService.tokenGetUser(request);
            GoodsDTO goodsDTO  = goodsService.updateGoods(user,goods_name,goodsId);
            return ResponseEntity.ok(goodsDTO);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("權限不足");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }
    }

    @DeleteMapping("/delete/{goodsId}/")
    public ResponseEntity<?> deleteGoods(HttpServletRequest request,@PathVariable Integer goodsId
    ){
        try{
            User user = jwtService.tokenGetUser(request);
            goodsService.deleteGoods(goodsId);
            return ResponseEntity.ok("成功");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("權限不足");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }
    }
}
