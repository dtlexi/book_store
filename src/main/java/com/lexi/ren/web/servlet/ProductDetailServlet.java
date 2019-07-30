package com.lexi.ren.web.servlet;

import com.lexi.ren.model.Product;
import com.lexi.ren.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class ProductDetailServlet extends HttpServlet {
    ProductService service=new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Product book=service.findBookById(id);
        if(book!=null)
        {
            req.setAttribute("book",book);
            req.getRequestDispatcher("/product_info.jsp").forward(req,resp);
        }
    }
}
