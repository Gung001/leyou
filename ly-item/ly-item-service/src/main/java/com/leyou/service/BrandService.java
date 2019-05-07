package com.leyou.service;

import com.leyou.pojo.Brand;
import com.leyou.vo.PageResult;

import java.util.List;

/**
 * @author Gryant
 */
public interface BrandService {


    /**
     * 品牌分页搜索
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    PageResult<Brand> queryByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    /**
     * 新增品牌
     * @param brand 品牌信息
     * @param cids 关联的商品分类信息
     */
    void saveBrand(Brand brand, List<Long> cids);
}
