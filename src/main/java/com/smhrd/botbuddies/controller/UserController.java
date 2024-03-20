package com.smhrd.botbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.User;
import com.smhrd.botbuddies.mapper.UserMapper;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper mapper;

    @RequestMapping("/search")
    public ResponseEntity<String> handleSearch(@RequestBody Map<String, String> requestData) {
        if ("성공".equals(requestData.get("data"))) {
        	System.out.println(requestData.get("data"));
            // 요청 데이터가 '성공'이면 '완료'를 응답합니다.
            return ResponseEntity.ok("완료");
        }
        return ResponseEntity.badRequest().body("실패");
    }

    @RequestMapping("/search_2")
    public ResponseEntity<String> handleSearch2(@RequestBody Map<String, String> requestData) {
        if ("성공".equals(requestData.get("data"))) {
        	System.out.println(requestData.get("data"));
            // 요청 데이터가 '성공'이면 '완료'를 응답합니다.
            return ResponseEntity.ok("완료");
        }
        return ResponseEntity.badRequest().body("실패");
    }

    @RequestMapping("/user_info")
    public List<User> user_info(@RequestBody Map<String, String> requestData) {
        System.out.println(requestData.get("data"));
        List<User> info = mapper.selectUser(); 
        System.out.println(info.toString());
        return  info;
    }

}
