package com.handler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.controller.IndexController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
*  打印所有请求信息
* @author Administrator
* @Time 2018-3-19
*  苦心修炼，水满自溢
*/

public class ActionLogInterceptor implements HandlerInterceptor{
   private boolean open = true;
   private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
   private long startTime=0;
   private String uri = null;
   private StringBuffer url =null;
   private String viewName = null;
   private Date date = null;
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
      startTime = System.currentTimeMillis();
      date = new Date();
      return true;
   }

   @Override
   public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
      if (modelAndView!=null)
         viewName = modelAndView.getViewName();
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
      if (!open){
         return;
      }
       uri = request.getRequestURI();
       url= request.getRequestURL();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
      logger.info("--------------------------action请求----------------------------:"+ dateFormat.format(date));
      String str = "";
      if (handler!=null){
         str =  handler.toString().trim();
         //去掉修饰符和参数
         int index = str.indexOf(" ");
         if(index!=-1) str = str.substring(index,str.length()-1).trim();
         index = str.indexOf(" ");
         if(index!=-1) str = str.substring(index+1,str.length()-1).trim();
         index = str.indexOf("(");
         if(index!=-1) str = str.substring(0,index).trim();
      }
      logger.info("uri: "+ uri);
      logger.info("url: "+ url);
      logger.info("method: "+ request.getMethod());
      logger.info("调用方法："+str);
      logger.info("参数：");
      Map parameterMap = request.getParameterMap();
      for (Object key:parameterMap.keySet()){
         String name = (String) key;
         String value = request.getParameter(name);
         logger.info(name + " : " + value);
      }

      if (viewName!=null)
         System.out.println("viewName: "+viewName);
      logger.info("耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
      logger.info("------------------------------------------------------: "+dateFormat.format(new Date()));
   }
   
}