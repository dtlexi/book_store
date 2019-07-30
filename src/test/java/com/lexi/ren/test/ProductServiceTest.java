package com.lexi.ren.test;

import com.lexi.ren.model.PageResult;
import com.lexi.ren.model.Product;
import com.lexi.ren.service.ProductService;
import org.junit.Test;

public class ProductServiceTest {
    ProductService service=new ProductService();
    @Test
    public void testFindBooksByPage(){
        PageResult<Product> result=service.findBooksByPage("计算机",1,4);
        System.out.println(result);
    }
}
