package com.smhrd.botbuddies.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreR;
import com.smhrd.botbuddies.entity.TableCount;
import com.smhrd.botbuddies.entity.Tabling;
import com.smhrd.botbuddies.entity.storeTable;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.RegisterStore;
import com.smhrd.botbuddies.entity.Reservation;
import com.smhrd.botbuddies.entity.Review;
import com.smhrd.botbuddies.mapper.StoreMapper;


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
    public StoreR selectStore(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq1 = requestData.get("store_seq");
        int store_seq = Integer.parseInt(store_seq1);
        System.out.println("매장 식별자"+store_seq);

        Store selecStores =  mapper.store1(store_seq);
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
        
    };

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
    };

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
    
    @PostMapping("/register")
    public void registerStore(@RequestBody RegisterStore response) {

        // 날짜 처리 부분
        ZonedDateTime zonedOpenTime = ZonedDateTime.parse(response.getOpen_time(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedEndTime = ZonedDateTime.parse(response.getEnd_time(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime openTime = zonedOpenTime.toLocalDateTime();
        LocalDateTime endTime = zonedEndTime.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedOpenTime = openTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);

        // 매장 정보 저장 부분
        // mapper.insertStore 호출 시 사용될 RegisterStore 객체 생성 또는 사용
        RegisterStore store = RegisterStore.builder()
                .store_name(response.getStore_name())
                .store_addr(response.getStore_addr())
                .store_phone(response.getStore_phone())
                .category_seq(response.getCategory_seq())
                .store_desc(response.getStore_desc())
                .open_time(formattedOpenTime)
                .end_time(formattedEndTime)
                .regiNum(response.getRegiNum())
                .user_id(response.getUser_id())
                .tabling_state(response.getTabling_state())
                .tableNum(response.getTableNum())
                .imgFilename(response.getImgFilename())
                .build();
        mapper.insertStore(store);

        // 이미지 저장 부분 - 생성된 store_seq 사용
        List<String> imgFilename = response.getImgFilename();
        for (String img : imgFilename) {
            mapper.insertStoreImage(img, store.getStore_seq());
        }

        Map<String, Integer> tableNumMap = response.getTableNum();
        tableNumMap.forEach((seats, count) -> {
            Integer tableSeats = Integer.parseInt(seats);
            for (int i = 0; i < count; i++) {
                mapper.insertStoreTable(tableSeats, store.getStore_seq());
            }
        });

    }

    @RequestMapping("/modify")
    public List<Store> requestMethodName(@RequestBody Map<String, String> response) {
        String store_seq = response.get("store_seq");
        System.out.println("들어옴~~~~~~~~~~~~~~~~~");
        System.out.println(store_seq);
        List<Store> selectstoredetails = mapper.selectStoreDetails(store_seq);
        System.out.println(selectstoredetails);
        return selectstoredetails;
    }

    @PostMapping("/updateStore")
    public void updateStore(@RequestBody RegisterStore registerStore) {
        // STORES 테이블 업데이트
        System.out.println(registerStore);
        mapper.updateStore(
                registerStore.getStore_name(),
                registerStore.getStore_addr(),
                registerStore.getStore_phone(),
                registerStore.getCategory_seq(),
                registerStore.getStore_desc(),
                registerStore.getTabling_state(),
                registerStore.getReserva_state(),
                registerStore.getOpen_time(),
                registerStore.getEnd_time(),
                registerStore.getStore_seq());

        // STORE_IMG 테이블 업데이트
        List<String> imgFilename = registerStore.getImgFilename();
        // 매장 정보 업데이트 로직 내에서
        if (imgFilename != null && !imgFilename.isEmpty()) {
            // 기존 이미지 데이터 삭제
            mapper.deleteStoreImagesByStoreSeq(registerStore.getStore_seq());

            // 새로운 이미지 데이터 삽입
            for (String img : imgFilename) {
                mapper.insertStoreImage(img, registerStore.getStore_seq());
            }
        }

        // STORE_TABLE 테이블 데이터 삭제
        mapper.deleteStoreTablesByStoreSeq(registerStore.getStore_seq());

        // STORE_TABLE 테이블 업데이트
        Map<String, Integer> tableNumMap = registerStore.getTableNum();
        if (tableNumMap != null) {
            tableNumMap.forEach((seats, count) -> {
                Integer tableSeats = Integer.parseInt(seats);
                for (int i = 0; i < count; i++) {
                    mapper.insertStoreTable(tableSeats, registerStore.getStore_seq());
                }
            });
        }

    }

    @PostMapping("/storename")
    public List<RegisterStore> getStoresByUserId(@RequestBody RegisterStore request) {
        String user_id = request.getUser_id();
        List<RegisterStore> stores = mapper.storename(user_id); // 매장 정보 조회
        System.out.println("매장 name, seq" + stores);
        return stores; // 조회된 매장 정보를 직접 반환
    }

    @PostMapping("/storemenu")
    public List<RegisterStore> getStoreMenus(@RequestBody RegisterStore request) {
        int store_seq = request.getStore_seq();
        List<RegisterStore> menus = mapper.storemenu(store_seq);
        System.out.println("메뉴 정보" + menus);
        return menus;
    }

    @PostMapping("/menustate")
    public String MenuState(@RequestBody RegisterStore request) {
        int updatedRows = mapper.menustate(request.getMenu_seq());
        if (updatedRows > 0) {
            // 업데이트 성공, 업데이트된 메뉴 상태 조회
            String menustate = mapper.selectmenustate(request.getMenu_seq());
            System.out.println("메뉴상태" + menustate);
            return menustate;
        } else {
            // 업데이트 실패 처리
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found or update failed");
        }
    }

    @PostMapping("/menuupdate")
    public List<RegisterStore> updateMenu(@RequestBody RegisterStore registerStore) {
        int menuupdate = mapper.updatemenu(registerStore);
        if (menuupdate > 0) {
            // 업데이트 성공 후, 변경된 데이터 조회
            List<RegisterStore> updatedMenu = mapper.storemenu(registerStore.getMenu_seq());
            return updatedMenu;
        } else {
            // 업데이트 실패 처리 (예시)
            throw new RuntimeException("Menu update failed.");
        }
    }

    @DeleteMapping("/menudelete/{menuSeq}")
    public ResponseEntity<String> deleteMenu(@PathVariable("menuSeq") int menuSeq) {
        mapper.deletemenu(menuSeq);
        // 메뉴 삭제 성공 메시지를 포함하여 응답 반환
        return ResponseEntity.ok("메뉴 삭제 성공");
    }

    @PostMapping("/insertmenu")
    public void insertMenu(@RequestBody RegisterStore registerStore) {
        System.out.println("메뉴 추가: " + registerStore.toString());
        mapper.insertmenu(registerStore.getStore_seq(), registerStore.getMenu_name(),
                registerStore.getMenu_desc(), registerStore.getPrice(), registerStore.getMenu_img());
    }



}
