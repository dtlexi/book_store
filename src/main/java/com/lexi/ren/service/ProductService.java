package com.lexi.ren.service;

import com.lexi.ren.dao.ProductDao;
import com.lexi.ren.model.PageResult;
import com.lexi.ren.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao dao=new ProductDao();

    public PageResult<Product> findBooksByPage(String category, int pageIndex, int pageSize) {
        if(pageIndex<=0)
        {
            throw new RuntimeException("当前页数不能小于0");
        }
        if(pageSize<=0)
        {
            throw new RuntimeException("数据错误");
        }
        try {
            long count=dao.count(category);
            if(count>0)
            {
                PageResult<Product> result=new PageResult<>();
                result.setCount(count);
                result.setPageindex(pageIndex);
                result.setPagesize(pageSize);
                result.setData(dao.findBooks(category,pageIndex,pageSize));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PageResult<>();
    }

    public Product findBookById(int id) {
        try {
            return dao.findBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
