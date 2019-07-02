package com.ljf.mall.service;

import com.ljf.mall.po.ProductCategory;

import java.util.List;

/**
 * Created by mr.lin on 2019/6/5
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
