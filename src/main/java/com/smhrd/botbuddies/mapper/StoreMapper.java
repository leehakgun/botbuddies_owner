package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Store;

@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    public List<Store> storeList(String id);

}
