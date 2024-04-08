package com.smhrd.botbuddies.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Review {

    private int review_seq;
    private String reviewUser_id;
    private String user_id;
    private int store_seq;
    private int order_num;
    private String title;
    private String details;
    private String answer;
    private String state;
    private int score;
    private String img_filename;
    private String user_nick;
   
    
}
