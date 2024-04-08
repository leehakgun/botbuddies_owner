package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.TableCount;
import com.smhrd.botbuddies.entity.Tabling;
import com.smhrd.botbuddies.entity.storeTable;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.RegisterStore;
import com.smhrd.botbuddies.entity.Reservation;
import com.smhrd.botbuddies.entity.Review;

@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    public List<Store> storelist(String id);

    public int inquiry(String id, String title, String details);

    public List<Store> reviewlist(int store_seq);

    public List<Integer> getStoreSeq(String id);

    public List<String> imgsearch(int review_seq);

    public void deleteStoreSeq(int store_seq);

    public List<Store> list(String user_id);

    public Store store1(int store_seq);

    public List<Reservation> reserveList(int store_seq);

    public List<Order> orderList(int store_seq);

    public List<Tabling> tablingList(int store_seq);

    public List<TableCount> tablecount(int store_seq);

    public List<storeTable> tableList(int store_seq);

    public int getTotal(int store_seq);

    public void reserveState(int reserve_seq);

    public void reserveStatecancel(int reserve_seq);

    public void ordercheck(int order_num);

    public void noshow(int wait_num);

    public void minustable(int tablesu, int store_seq, int table_num);

    public void all(int store_seq, int table_num);

    public void plustable(int state, int store_seq, int table_num);

    public void sendconfirmation(String user_id);

    public void sendcancel(String user_id);

    public void sendnoshow(String user_id);

    public void sendcomeon(String user_id);

    public void completeStand(int wait_num);

    public void sendstand(String user_id);

    public int aftersend(String user_id);

    public List<Store> store(int store_seq);

    public void deleteans(int reivew_seq);

    public void insertans(String answer, int review_seq);

    public void updateans(String answer, int review_seq);

    public List<Review> searchrv(String startDate, String endDate, int store_seq);

    public void insertStore(RegisterStore store);

    public void insertStoreImage(String imgFilename, int store_seq);

    public void insertStoreTable(int tableNum, int store_seq);

    public List<Store> selectStoreDetails(String store_seq);

    public void updateStore(String store_name, String store_addr, String store_phone, String category_seq,
            String store_desc, int tabling_state, int reserva_state, String open_time, String end_time, int store_seq);

    public void updateStoreImg(List<String> imgfilename, int img_seq);

    public void deleteStoreTablesByStoreSeq(int store_seq);

    public void deleteStoreImagesByStoreSeq(int store_seq);

    public List<RegisterStore> storename(String user_id);

    public List<RegisterStore> storemenu(int store_seq);

    public int menustate(int menu_seq);

    public String selectmenustate(int menu_seq);

    public int updatemenu(RegisterStore registerStore);

    public void deletemenu(int menu_seq);

    public void insertmenu(int store_seq, String menu_name, String menu_desc, int price, String menu_img);
};
