package com.smhrd.botbuddies.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreR {
    
    public StoreR(int store_seq, List<Store> storeInfo) {
        this.store_seq = store_seq;
        this.storeInfo = storeInfo;
    }

    private int store_seq;
    private List<Store> storeInfo;
    private List<String> img_filename;


    
    // 기타 필드와 메소드 생략...

    public StoreR(int store_seq, List<Store> storeInfo, List<String> img_filename) {
        this.store_seq = store_seq;
        this.storeInfo = storeInfo;
        this.img_filename = img_filename;
    }

    // storeSeq 필드의 getter 메소드
    public int getStoreSeq() {
        return this.store_seq;
    }

    private Store store;
    private List<Reservation> reservaList;
    private List<Order> orederList;
    private List<Tabling> tablingList;
    private List<storeTable> tableList;
    private int total_pay;
    private List<TableCount> tablecount;
    
    

    public StoreR(Store store, List<Reservation> reservaList, List<Order> orederList, List<Tabling> tablingList,
            List<storeTable> tableList,List<TableCount> tablecount,int total_pay) {
        this.store = store;
        this.reservaList = reservaList;
        this.orederList = orederList;
        this.tablingList = tablingList;
        this.tableList = tableList;
        this.tablecount = tablecount;
        this.total_pay = total_pay;
        
        
    }
    
    

    
}

