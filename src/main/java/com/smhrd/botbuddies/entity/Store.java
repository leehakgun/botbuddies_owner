package com.smhrd.botbuddies.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Store {

    private int store_seq;
    private String user_id;
    private String store_name;
    private String store_addr;
    private String store_phone;
    private String category_seq;
    private String store_desc;
    private String tabling_state;
    private String state;
    private String open_time;
    private String end_time;
    private List<String> img_filename;
    private String user_nick;

    private String review_seq;
    private String title;
    private String details;
    private String answer;
    private String score;
    private String create_at;
    private String reviewUserNick;


    public Store(int store_seq, String store_name, List<String> img_filename, String user_id) {
        this.store_seq = store_seq;
        this.store_name = store_name;
        this.img_filename = img_filename;
        this.user_id = user_id;
    }

    
    public Store(int store_seq,String store_name,String user_id,String reviewUserNick , String review_seq,String title,String details,String answer,  String state,String score, String img_filename,String create_at
                ) {
        this.store_seq = store_seq;
        this.reviewUserNick= reviewUserNick;
        this.user_id = user_id;
        this.store_name = store_name;
        this.state = state;
        this.img_filename = img_filename;
        this.review_seq = review_seq;
        this.title = title;
        this.details = details;
        this.answer = answer;
        this.score = score;
        this.create_at = create_at;
        
    }


    public Store(String store_name) {
       
        this.store_name = store_name;
        
    }

}
