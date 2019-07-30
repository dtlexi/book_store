package com.lexi.ren.web.servlet;

import com.lexi.ren.model.PageResult;
import com.lexi.ren.model.Product;
import com.lexi.ren.service.ProductService;
import com.mchange.v2.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showProductByPage")
public class ProductsListServlet extends HttpServlet {
    ProductService service=new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category=req.getParameter("category");
        int pageIndex= StringUtils.nonEmptyString(req.getParameter("page"))? Integer.parseInt(req.getParameter("page")):1;
        int pageSize=4;
        PageResult<Product> result=service.findBooksByPage(category,pageIndex,pageSize);
        if(result!=null)
        {
            System.out.println(result);
            req.setAttribute("pageresult",result);
            req.setAttribute("category",category);
            req.getRequestDispatcher("/product_list.jsp").forward(req,resp);
        }
    }
}
