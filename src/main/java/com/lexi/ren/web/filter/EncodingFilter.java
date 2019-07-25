package com.lexi.ren.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //1.设置POST请求中文乱码的问题
        request.setCharacterEncoding("UTF-8");
        System.out.println("拦截请求:" + request);

        //2.解决get请求的中文乱码问题
        //request ： RequestFacade;
        HttpServletRequest hsr = (HttpServletRequest) request;
        if (hsr.getMethod().equalsIgnoreCase("get")) {
            MyRequest myRequest = new MyRequest(hsr);
            //放行请求
            chain.doFilter(myRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}


class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private boolean isEncoding = false;//是否已经utf-8编码
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        return getParameterMap().get(name)[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = request.getParameterMap();

        if(isEncoding == true){
            return map;
        }

        //遍历vlaue，改成utf-8编码
        for(Map.Entry<String, String[]> entry : map.entrySet()){
            //取数组值
            String[] values = entry.getValue();
            for(int i=0;i<values.length;i++){
                try {
                    values[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        isEncoding = true;

        return map;
    }
}