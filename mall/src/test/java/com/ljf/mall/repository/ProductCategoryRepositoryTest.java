package com.ljf.mall.repository;

import com.ljf.mall.po.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mr.lin on 2019/6/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository repository;

    @Test
    public void insert() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("类别1");
        productCategory.setCategoryType(1);
        Assert.assertNotNull(repository.save(productCategory));
    }

    @Test
    public void select() {
        Assert.assertNotNull(repository.findById(1).get());
    }

    @Test
    public void update() {
        ProductCategory productCategory = repository.findById(1).get();
        productCategory.setCategoryName("类别11");
        productCategory.setCategoryType(11);
        Assert.assertNotNull(repository.save(productCategory));
    }

    @Test
    public void delete() {
        ProductCategory productCategory = repository.findById(1).get();
        repository.delete(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() {
        List list = Arrays.asList(1, 2, 3);
        Assert.assertNotEquals(0, repository.findByCategoryTypeIn(list).size());
    }


}