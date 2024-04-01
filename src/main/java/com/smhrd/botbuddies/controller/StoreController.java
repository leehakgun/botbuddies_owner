package com.smhrd.botbuddies.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreR;

import com.smhrd.botbuddies.entity.Review;
import com.smhrd.botbuddies.mapper.StoreMapper;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class StoreController {
   
    @Autowired
    private StoreMapper mapper;


       @RequestMapping("/storelist")
    public List<Store> storelist(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음시작");
        String id = requestData.get("userId");
       
        System.out.println(id);
        
        List<Store> list = mapper.storelist(id); 

        for(Store i : list){
            System.out.println(i.toString());
        }

        // System.out.println(info.toString());
        
        return list;
        
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerStore(@RequestBody Store store) {
        System.out.println(store);
        mapper.insertStore(store);
    
        // 추가적으로 StoreImage, StoreTable 정보 처리
        return ResponseEntity.ok("Store registered successfully");
    }

    // @PostMapping("/review")
    // public ResponseEntity<String> registerStore(@RequestBody Review review) {
    //     System.out.println(review);
    //     mapper.reviewList(review);
    
    //     // 추가적으로 StoreImage, StoreTable 정보 처리
    //     return ResponseEntity.ok("Store registered successfully");
    // }

    @RequestMapping("/inquiry")
    public int inquiry(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String id = requestData.get("userId");
        String title = requestData.get("title");
        String details = requestData.get("details");
       
        System.out.println("리뷰"+id);
        
        int cnt = mapper.inquiry(id, title, details); 
        System.out.println(cnt);
        if (cnt==0){
            System.out.println("실패");
        }else {
            System.out.println("성콩");
        }
         return cnt;
        
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

        System.out.println(storeRList);

        return storeRList;
        
    }


    @RequestMapping("/deleteStore")
    public ArrayList<StoreR> deleteStore(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq1 = requestData.get("storeSeq");
        String user_id = requestData.get("userId");
        int store_seq = Integer.parseInt(store_seq1);
        
        System.out.println("리뷰"+store_seq);
        System.out.println("아이디"+user_id); 

        mapper.deleteStoreSeq(store_seq);

        List<Integer> storeSeq =  mapper.getStoreSeq(user_id);

        ArrayList<StoreR> storeRList = new ArrayList<>();

        for(int seq : storeSeq ){
            List<Store> review = mapper.reviewlist(seq);
            StoreR store = new StoreR(store_seq, review);
            storeRList.add(store);
        }


        return storeRList;
       
    }


    @RequestMapping("/selectStore")
    public List<Store> selectStore(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        System.out.println("매장 식별자"+store_seq);

        List<Store> selecStores =  mapper.store(store_seq);

        System.out.println("가게 설명"+selecStores);
        // System.out.println(info.toString());
        
        return selecStores;
        
    }




    

}
