package com.smhrd.botbuddies.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreR;
import com.smhrd.botbuddies.entity.User;
import com.smhrd.botbuddies.mapper.StoreMapper;



@RestController
public class StoreController {

    @Autowired
    private StoreMapper mapper;


       @RequestMapping("/storelist")
    public List<Store> storelist(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String id = requestData.get("userId");
       
        System.out.println(id);
        
        List<Store> list = mapper.storelist(id); 
        System.out.println(list.get(0).toString());
        // System.out.println(info.toString());
        
        return list;
        
    }


    @RequestMapping("/review")
    public ArrayList<StoreR> review(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String id = requestData.get("userId");
       
        System.out.println("리뷰"+id);

        List<Integer> storeSeq =  mapper.getStoreSeq(id);

        ArrayList<StoreR> storeRList = new ArrayList<>();

        
        
        for(int store_seq : storeSeq ){
            List<Store> review = mapper.reviewlist(store_seq);
            List<String> img_filename = new ArrayList<>();
             img_filename = mapper.imgsearch(store_seq);
            StoreR store = new StoreR(store_seq, review, img_filename);
            storeRList.add(store);
        }

        
        // System.out.println(info.toString());
        System.out.println(storeRList);
        return storeRList;
        
    }




}
