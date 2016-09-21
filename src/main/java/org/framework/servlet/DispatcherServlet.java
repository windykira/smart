package org.framework.servlet;

import org.framework.handler.Handler;
import org.framework.helper.BeanHelper;
import org.framework.helper.ConfigHelper;
import org.framework.helper.ControllerHelper;
import org.framework.helper.HelperLoader;
import org.framework.utils.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by windy on 2016/9/20.
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        //初始化Helper相关类
        HelperLoader.init();
        //获取ServletContext对象
        ServletContext servletContext = config.getServletContext();
        //处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //获取请求方法与请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler != null)
        {
            //获取controller类及bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //创建请求参数对象
            Map<String,Object> paramMap = new HashMap();
            Enumeration<String> paramNames = request.getParameterNames();
            String paramName;
            String paramValue;
            while(paramNames.hasMoreElements())
            {
                paramName = paramNames.nextElement();
                paramValue = request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if(StringUtil.isNotEmpty(body))
            {
                String[] params = body.split("&");
                if(ArrayUtil.isNotEmpty(params))
                {
                    String[] array;
                    String paramName_;
                    String paramValue_;
                    for(String param : params)
                    {
                        array = param.split("=");
                        if(ArrayUtil.isNotEmpty(array) && array.length == 2)
                        {
                            paramName_ = array[0];
                            paramValue_ = array[1];
                            paramMap.put(paramName_,paramValue_);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            //调用Action方法
            Method actionMethod = handler.getMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
            //处理Action方法返回值
            if(result instanceof View)
            {
                //返回JSP页面
                View view = (View)result;
                String path = view.getPath();
                if(StringUtil.isNotEmpty(path))
                {
                    if(path.startsWith("/"))
                    {
                        response.sendRedirect(request.getContextPath() + path);
                    }else
                    {
                        Map<String,Object> model = view.getModel();
                        for(Map.Entry<String,Object> entry : model.entrySet())
                        {
                            request.setAttribute(entry.getKey(),entry.getValue());
                        }
                        request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request,response);
                    }
                }
            }else if(result instanceof Data)
            {
                //返回JSON数据
                Data data = (Data)result;
                Object model = data.getModel();
                if(model != null)
                {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }



}
