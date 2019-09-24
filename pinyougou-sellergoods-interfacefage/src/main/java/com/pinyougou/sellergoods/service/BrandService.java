package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    //分页
    PageInfo<TbBrand> findAll(int page,int size,TbBrand tbBrand);
    PageInfo<TbBrand> findAll1();
    //添加品牌
    int add(TbBrand tbBrand);

    TbBrand findById(Long id);

    int updateBrand(TbBrand tbBrand);

    int deleteById(List<Long> ids);
}
