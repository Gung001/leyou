package com.leyou.service;

import com.leyou.pojo.Category;

import java.util.List;

/**
 * @author Gryant
 */
public interface CategoryService {

    List<Category> queryCategoryByPid(Long pid);

}
