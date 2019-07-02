package com.ljf.product.service;

import com.ljf.product.dataobject.ProductInfo;
import com.ljf.product.dto.CartDTO;

import java.util.List;

/**
 * Created by mr.lin on 2019/6/19
 */
public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdList);

    void decreaseStock(List<CartDTO> cartDTOList);

}
