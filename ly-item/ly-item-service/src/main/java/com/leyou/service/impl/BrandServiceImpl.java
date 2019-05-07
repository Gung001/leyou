package com.leyou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.mapper.BrandMapper;
import com.leyou.pojo.Brand;
import com.leyou.service.BrandService;
import com.leyou.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Gryant
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        // 开始分页
        PageHelper.startPage(page, rows);

        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key);
        }

        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }

        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);

        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {

        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new LyException(ExceptionEnum.SAVE_BRAND_ERROR);
        }

        // 中间表数据
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count != 1) {
                throw new LyException(ExceptionEnum.SAVE_BRAND_ERROR);
            }
        }

    }
}
