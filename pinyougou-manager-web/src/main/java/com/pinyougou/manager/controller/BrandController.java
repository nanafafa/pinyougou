package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;



    /**
     * 修改品牌  先通过id查
     */
    @RequestMapping("/{id}")
    public TbBrand findById(@PathVariable("id") Long id){
        TbBrand tbBrand=brandService.findById(id);
        return tbBrand;
    }
    /**
     * 修改品牌
     */
    @RequestMapping("/modify")
    public Result modifyByBrand(@RequestBody TbBrand tbBrand){
        try{
            int count=brandService.updateBrand(tbBrand);
            if (count>0){
               return new Result(true,"修改成功");
            }
        }catch (Exception e){
            return new Result(false,"修改失败");
        }
        return null;
    }

    /**
     * 增加品牌
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    public Result add(@RequestBody TbBrand tbBrand){
        //Map<String, Object> map = new HashMap<>();
        try {
            int count=brandService.add(tbBrand);
            if (count>0){
                //map.put("success","true");
                //map.put("message","添加成功");
                return new Result(true,"添加成功");
            }
        }catch (Exception e){
            //map.put("success","false");
            //map.put("message","添加失败");
            return new Result(false,"添加失败");
        }
        return null;
    }
    /**
     * 分页
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageInfo<TbBrand> list(@RequestParam(value = "page",required = false,defaultValue = "1") int page,
                                  @RequestParam(value = "size",required = false,defaultValue = "10") int size,
                                  @RequestBody TbBrand tbBrand){
        PageInfo<TbBrand> pageInfo = brandService.findAll(page, size,tbBrand);


        return pageInfo;
    }
    /**
     * 删除品牌  多选的
     *
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteByIds(@RequestBody List<Long> ids){
        try{
            int count=brandService.deleteById(ids);
            if(count>0){
                return new Result(true,"删除成功");
            }
        }catch(Exception e){
            return new Result(false,"删除失败");
        }
        return null;
    }





    /**
     * 测试
     * @return
     */
    @RequestMapping("/findAll")
    public PageInfo<TbBrand> findAll(){
        PageInfo<TbBrand> list=brandService.findAll1();
        return list;
    }

    /**
     *
     * {"success":"true","message":"成功"}
     *
     * @return
     */
    @RequestMapping("/ceshi")
    public Map<String,Object> ceshiDemo(){
        Map<String, Object> map = new HashMap<>();
        map.put("success","true");
        map.put("message","成功");
        return map;
    }

}
