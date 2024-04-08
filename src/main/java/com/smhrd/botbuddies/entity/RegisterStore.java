package com.smhrd.botbuddies.entity;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor // Jackson 역직렬화를 위한 기본 생성자 추가
@AllArgsConstructor
@ToString
@Builder
public class RegisterStore {
    private Map<String, Integer> tableNum;
    private List<String> imgFilename;
    private String store_name;
    private String store_addr;
    private int store_seq; // 생성 시점에는 이 필드가 불필요할 수 있으니 생성자에서 제외하는 것을 고려
    private String store_phone;
    private String category_seq;
    private String store_desc;
    private String open_time;
    private String end_time;
    private String regiNum;
    private String user_id;
    private int tabling_state;
    private int reserva_state;
    private int img_seq;
    private int table_seq;
    private String menu_name;
    private String menu_desc;
    private String menu_img;
    private int price;
    private int menu_seq;
    private String menu_state;


}
