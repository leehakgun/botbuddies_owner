package com.smhrd.botbuddies.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreR;

@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    public List<Store> storeList(String id);

    public List<Store> storelist(String id);

    public List<Store> reviewlist(int store_seq);

    public List<Integer> getStoreSeq (String id);

    public List<String> imgsearch(int review_seq);

}


