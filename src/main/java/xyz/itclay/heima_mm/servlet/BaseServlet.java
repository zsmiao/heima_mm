package xyz.itclay.heima_mm.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author ZhangSenmiao
 * @date 2021/3/5 10:37
 **/
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取uri
        String uri = req.getRequestURI();
        //2、获取执行的方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            //3、获取Method对象（反射）
            //3.1 方法名
            //3.2 参数列表：HttpServletRequest,HttpServletResponse
            //3.3 方法所在的对象:this
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            method.setAccessible(true);
            //执行this对象上带有req和resp的method方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
