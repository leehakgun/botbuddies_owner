package com.smhrd.botbuddies.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.event.TableColumnModelEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreR;
import com.smhrd.botbuddies.entity.TableCount;
import com.smhrd.botbuddies.entity.Tabling;
import com.smhrd.botbuddies.entity.storeTable;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.Reservation;
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
    public StoreR selectStore(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        System.out.println("매장 식별자"+store_seq);

        Store selecStores =  mapper.store(store_seq);
        List<Reservation> reserveList = mapper.reserveList(store_seq);

        List<Order> orderList = mapper.orderList(store_seq);
        List<Tabling> tablingList = mapper.tablingList(store_seq);
        List<storeTable> tableList = mapper.tableList(store_seq);
        List<TableCount> tablecount = mapper.tablecount(store_seq);
        int total_pay = mapper.getTotal(store_seq);
        
        StoreR storeInfo = new StoreR(selecStores, reserveList, orderList, tablingList, tableList, tablecount ,total_pay);
        
         
        return storeInfo;
        
    }

    @RequestMapping("/reserveState")
    public List<Reservation> reserveState(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String reserve_seq1 = requestData.get("reserve_seq");
        int reserve_seq = Integer.parseInt(reserve_seq1);
        System.out.println("예약 식별자"+reserve_seq);
        String user_id = requestData.get("user_id");
        mapper.sendconfirmation(user_id);
        mapper.reserveState(reserve_seq); 

        String store_seqS = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seqS);

        System.out.println("store_seq"+store_seq);

        List<Reservation> reserveList = mapper.reserveList(store_seq);
       
        return reserveList;
       
    }


    @RequestMapping("/reserveStatecancel")
    public List<Reservation> reserveStatecancel(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String reserve_seq1 = requestData.get("reserve_seq");
        int reserve_seq = Integer.parseInt(reserve_seq1);
        System.out.println("예약 식별자"+reserve_seq);
        String user_id = requestData.get("user_id");

        String store_seqS = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seqS);

        System.out.println("store_seq"+store_seq);
        
        mapper.sendcancel(user_id);
        mapper.reserveStatecancel(reserve_seq); 

        List<Reservation> reserveList = mapper.reserveList(store_seq);
       
        return reserveList;
    }


    @RequestMapping("/ordercheck")
    public void ordercheck(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String order_num1 = requestData.get("order_num");
        int order_num = Integer.parseInt(order_num1);
        System.out.println("주문 식별자"+order_num);
        
        mapper.ordercheck(order_num); 
       
    }

    @RequestMapping("/noshow")
    public void noshow(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String wait_num1 = requestData.get("wait_num");
        int wait_num = Integer.parseInt(wait_num1);
        System.out.println("주문 식별자"+wait_num);
        String user_id = requestData.get("user_id");

        mapper.sendnoshow(user_id);
        mapper.noshow(wait_num); 
       
    }

    @RequestMapping("/comeon")
    public int comeon(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String wait_num1 = requestData.get("wait_num");
        int wait_num = Integer.parseInt(wait_num1);
        System.out.println("주문 식별자"+wait_num);
        String user_id = requestData.get("user_id");

        mapper.sendcomeon(user_id);
        int cnt = mapper.aftersend(user_id);

        return cnt;
       
       
    }

    @RequestMapping("/completeStand")
    public void completeStand(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String wait_num1 = requestData.get("wait_num");
        int wait_num = Integer.parseInt(wait_num1);
        System.out.println("주문 식별자"+wait_num);
        String user_id = requestData.get("user_id");

        mapper.completeStand(wait_num);
       mapper.sendstand(user_id);
       
    }



    @RequestMapping("/minustable")
    public void minustable(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String tablesu1 = requestData.get("tablesu");
        int tablesu = Integer.parseInt(tablesu1);
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        String table_num1 = requestData.get("table_num");
        int table_num = Integer.parseInt(table_num1);
       
        System.out.println("리뷰"+tablesu+store_seq+table_num);
        
        
        for (int i = 0; i < tablesu; i++) {
            // table_num이 반복마다 변해야 한다면, i를 사용하여 table_num을 조정할 수 있습니다.
            // 예: mapper.minustable(tablesu, store_seq, table_num + i);
            mapper.minustable(tablesu,store_seq, table_num);
        }
        
    }

    @RequestMapping("/plustable")
    public void plustable(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String tablesu1 = requestData.get("tablesu");
        int tablesu = Integer.parseInt(tablesu1);
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        String table_num1 = requestData.get("table_num");
        int table_num = Integer.parseInt(table_num1);
        String state1 = requestData.get("state");
        int state = Integer.parseInt(state1);
        System.out.println("리뷰"+tablesu+store_seq+table_num+state);
        
        mapper.all(store_seq, table_num);
        if (state == tablesu) {
            
            mapper.all(store_seq, table_num);
        }else {
            mapper.plustable(state-2,store_seq, table_num);
        }        
        
    }

}
