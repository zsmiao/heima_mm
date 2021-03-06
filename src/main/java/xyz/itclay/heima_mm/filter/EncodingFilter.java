package xyz.itclay.heima_mm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 解决POST提交中文乱码过滤器
* */
@WebFilter(value = "/*",initParams ={@WebInitParam(name = "encoding",value = "UTF-8")} )
public class EncodingFilter implements Filter {
    private  String encoding ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         encoding = filterConfig.getInitParameter("encoding");
        System.out.println(encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1、对象转换
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //2、设置请求和响应的字符集编码
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset="+encoding);
        //3、放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
