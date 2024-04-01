package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smhrd.botbuddies.entity.Store;



import com.smhrd.botbuddies.entity.Review;


@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    // public List<Store> storeList(String id);


    public List<Store> storelist(String id);

    public void insertStore(Store store);
    
    public void insertStoreImage(@Param("storeSeq") Long storeSeq, @Param("imgFilename") String imgFilename);

    public void insertStoreTable(@Param("storeSeq") Long storeSeq, @Param("tableNum") Integer tableNum, @Param("tableState") String tableState);


    public int inquiry(String id, String title, String details);


    public List<Store> reviewlist(int store_seq);

    public List<Integer> getStoreSeq (String id);


    public List<String> imgsearch(int review_seq);



    
    public void deleteStoreSeq (int store_seq);

    public List<Store> list(String user_id);
    
    public List<Store> store(int store_seq);
};

