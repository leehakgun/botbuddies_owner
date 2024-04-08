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

    
    @RequestMapping("/Deleteanswer")
    public void Deleteanswer(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String review_seq1 = requestData.get("review_seq");
        int review_seq = Integer.parseInt(review_seq1);
        System.out.println("매장 답변"+review_seq);

        mapper.deleteans(review_seq);
       
    }
    @RequestMapping("/insertans")
    public ArrayList<StoreR> insertans(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String review_seq1 = requestData.get("review_seq");
        int review_seq = Integer.parseInt(review_seq1);
        System.out.println("매장 식별자"+review_seq);
        String answer = requestData.get("answer");
        System.out.println("사장 답변"+answer);
        mapper.insertans(answer,review_seq);

        String id = requestData.get("user_id");

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

    @RequestMapping("/updateans")
    public ArrayList<StoreR> updateans(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String review_seq1 = requestData.get("review_seq");
        int review_seq = Integer.parseInt(review_seq1);
        System.out.println("매장 식별자"+review_seq);
        String answer = requestData.get("answer");
        System.out.println("사장 답변"+answer);
        mapper.insertans(answer,review_seq);

        String id = requestData.get("user_id");

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
    @RequestMapping("/search_review")
    public List<Review> search_review(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String startDate = requestData.get("startDate");
        String endDate = requestData.get("endDate");
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        System.out.println("시작"+startDate);
        System.out.println("끝"+endDate);
        System.out.println("매장 식별자"+store_seq);

        List<Review> searchReview = mapper.searchrv(startDate,endDate,store_seq);

        return searchReview;
       
    }
    



}
