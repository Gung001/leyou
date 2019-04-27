package com.leyou.service;

import com.leyou.pojo.Brand;
import com.leyou.vo.PageResult;

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
}
