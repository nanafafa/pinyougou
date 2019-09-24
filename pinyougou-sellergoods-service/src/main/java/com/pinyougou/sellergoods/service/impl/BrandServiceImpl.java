package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;

import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public PageInfo<TbBrand> findAll(int page, int size,TbBrand tbBrand) {
        //构造分页的数据
        PageHelper.startPage(page,size);
        TbBrandExample brandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = brandExample.createCriteria();
        if(tbBrand!=null){
            if(StringUtils.isNotBlank(tbBrand.getName())){

                criteria.andNameLike("%"+tbBrand.getName()+"%");

            }

            if(StringUtils.isNotBlank(tbBrand.getFirstChar())){
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
            }

        }
        List<TbBrand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(brands);
        return pageInfo;
    }


    /**
     * 分页测试
     */
    @Override
    public PageInfo<TbBrand> findAll1() {
        PageHelper.startPage(1,10);
        List<TbBrand> brands = brandMapper.selectByExample(null);
        PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(brands);
        return pageInfo;
    }

    /**
     * 添加品牌
     * @param tbBrand
     * @return
     */
    @Override
    public int add(TbBrand tbBrand) {

        return brandMapper.insertSelective(tbBrand);
    }

    @Override
    public TbBrand findById(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateBrand(TbBrand tbBrand) {

        return brandMapper.updateByPrimaryKeySelective(tbBrand);
    }

    @Override
    public int deleteById(List<Long> ids) {
        TbBrandExample brandExample = new TbBrandExample();
         brandExample.createCriteria().andIdIn(ids);
        return brandMapper.deleteByExample(brandExample);
    }


}
