package com.smhrd.botbuddies.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.mapper.StoreMapper;



@RestController
public class StoreController {

    @Autowired
    private StoreMapper mapper;

    @RequestMapping("/storeList")
    public List<Store> user_info(@RequestBody Map<String, String> requestData) {
        System.out.println(requestData.get("id"));
        String id = requestData.get("id");
        List<Store> storeList = null;

        if(id.equals("0")){
            storeList = mapper.storeListAll();
        } else{
            storeList = mapper.storeList(id);
        }
        
        System.out.println(storeList.toString());

        return storeList;
        
        
    }

}
