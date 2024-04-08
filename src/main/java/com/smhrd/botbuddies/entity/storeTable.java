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
public class storeTable {
    private int table_seq;
    private int store_seq;
    private int table_num;
    private String table_state;
    private int label_count;
    
    private int state_count;
    
    public storeTable(int store_seq, int table_num, String table_state,int state_count) {
        this.store_seq = store_seq;
        this.table_num = table_num;
        this.table_state = table_state;
        this.state_count=state_count;
    }
}
