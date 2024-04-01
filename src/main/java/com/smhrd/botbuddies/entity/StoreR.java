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
    
    private int store_seq;
    private List<Store> storeInfo;
    private List<String> img_filename;
    // 기타 필드와 메소드 생략...

    // storeSeq 필드의 getter 메소드
    public int getStoreSeq() {
        return this.store_seq;
    }

}
