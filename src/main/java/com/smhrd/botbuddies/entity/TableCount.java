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

public class TableCount {
 
    private int label_count;
    private int table_seq;
    private int store_seq;
    private int table_num;
    private String table_state;
    
    public TableCount(int label_count, int table_seq, int store_seq, int table_num, String table_state) {
        this.label_count = label_count;
        this.table_seq = table_seq;
        this.store_seq = store_seq;
        this.table_num = table_num;
        this.table_state = table_state;
    }
}
