package com.smhrd.botbuddies.entity;


import java.util.List;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Store {

    private int store_seq;
    private String user_id;
    private String reviewUser_id;
    private String store_name;
    private String store_addr;
    private String store_phone;
    private String category_seq;
    private String store_desc;
    private String tabling_state;
    private String reserva_state;
    private String state;
    private String open_time;
    private String end_time;

    private String img_filename;
    private String user_nick;
   
    private String review_seq;
    private String title;
    private String details;
    private String answer;
    private String score;
    private String create_at;
    private String reviewUserNick;
    private String reviewImageFilenames;

    public Store(int store_seq, String store_name, String img_filename, String user_id) {
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

    private String regi_num;
    private String open_date;
    private String open_state;
    private String regi_at;




    public Store(int store_seq, String user_id, String store_name, String store_addr, String store_phone,
            String category_seq, String store_desc, String tabling_state, String reserva_state, String state,
            String open_time, String end_time, String regi_num, String open_date, String open_state, String regi_at) {
        this.store_seq = store_seq;
        this.user_id = user_id;
        this.store_name = store_name;
        this.store_addr = store_addr;
        this.store_phone = store_phone;
        this.category_seq = category_seq;
        this.store_desc = store_desc;
        this.tabling_state = tabling_state;
        this.reserva_state = reserva_state;
        this.state = state;
        this.open_time = open_time;
        this.end_time = end_time;
        this.regi_num = regi_num;
        this.open_date = open_date;
        this.open_state = open_state;
        this.regi_at = regi_at;
    }




    public Store(int store_seq, String store_name) {
        this.store_seq = store_seq;
        this.store_name = store_name;
    }

  

}
