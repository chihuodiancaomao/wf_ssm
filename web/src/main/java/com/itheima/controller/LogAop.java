package com.itheima.controller;


import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

@Before("execution(* com.itheima.controller.ProductController.*(..))")
    public void logBefore(JoinPoint jp) throws Exception {

        //开始访问的时间
       startTime = new Date();

        //访问类名
        executionClass = jp.getTarget().getClass();

        //获取执行的方法名
        String methodName = jp.getSignature().getName();

        //获取执行方法的参数
        Object[] args = jp.getArgs();

        //获取访问的方法
        if(args.length==0){
            executionMethod = executionClass.getMethod(methodName);
        }else{
           Class [] classArgs = new Class [args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,classArgs);
        }
    }

@After("execution(* com.itheima.controller.ProductController.*(..))")
    public void logAfter(JoinPoint jp){

        SysLog sysLog = new SysLog();

        //存入开始访问的日期
        sysLog.setVisitTime(startTime);

       //存入访问的方法
       sysLog.setMethod(executionClass.getName()+" "+executionMethod.getName() );


       //获取IP并存入
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);


        //获取用户名并存入
       // 可以通过securityContext获取,也可以从request.getSession中获取
        //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
        sysLog.setUsername(username);


        //获取url并存入
        //获取类上的url
        if(executionClass!=null && executionClass!=LogAop.class) {
            //获取类上的RequestMapping注解
            RequestMapping classAnnotation = (RequestMapping)executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                //获取方法上的RequestMapping注解
                RequestMapping methodAnnotation = (RequestMapping)executionMethod.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    sysLog.setUrl(classAnnotation.value()[0]+methodAnnotation.value()[0]);
                }
            }
        }


        //存入访问用时
        Date endTime = new Date();
        sysLog.setExecutionTime(endTime.getTime()-startTime.getTime());

        //操作信息存入日志表中
        sysLogService.save(sysLog);
    }

}

//    private Date visitTime;
//    private String username;
//    private String ip;
//    private String url;
//    private Long executionTime;
//    private String method;
