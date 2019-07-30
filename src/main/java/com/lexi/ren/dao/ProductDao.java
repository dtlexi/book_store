package com.lexi.ren.dao;

import com.lexi.ren.model.Product;
import com.lexi.ren.utils.C3P0Utils;
import com.mchange.v2.lang.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public long count(String category) throws SQLException {
        QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select count(id) from products where 1=1 ";
        if(StringUtils.nonEmptyString(category))
        {
            sql+=" and category=? ";
            return (long) runner.query(sql,new ScalarHandler(1), category);
        }else {
            return (long) runner.query(sql,new ScalarHandler(1));
        }
    }

    public List<Product> findBooks(String category, int pageIndex, int pageSize) throws SQLException {
        List<Object> prmts = new ArrayList<>();
        QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from products where 1=1 ";
        if(StringUtils.nonEmptyString(category))
        {
            sql+=" and category=? ";
            prmts.add(category);
        }
        sql+=" limit ?,?";
        prmts.add((pageIndex-1)*pageSize+1);
        prmts.add(pageSize);
        return runner.query(sql,new BeanListHandler<Product>(Product.class),prmts.toArray());
    }

    public Product findBookById(int id) throws SQLException {
        String sql="select * from products where id=?";
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        return qr.query(sql,new BeanHandler<Product>(Product.class),id);
    }
}
