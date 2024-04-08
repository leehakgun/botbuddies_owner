package com.smhrd.botbuddies.entity;

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
public class Reservation {

    private int reserve_seq;
    private String user_id;
    private int store_seq;
    private String reserve_name;
    private String reserve_date;
    private String reserve_time;
    private int reserve_num;
    private String state;
    private String reserve_at;
    
}
