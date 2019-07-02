package com.ljf.product.service;

import com.ljf.product.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by mr.lin on 2019/6/19
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
