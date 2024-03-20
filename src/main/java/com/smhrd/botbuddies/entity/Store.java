package com.smhrd.botbuddies.entity;

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

}
