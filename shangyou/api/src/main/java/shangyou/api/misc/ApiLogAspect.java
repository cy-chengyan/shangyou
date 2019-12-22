package shangyou.api.misc;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import shangyou.core.common.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Pointcut("execution(* shangyou.api.controller..*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String queryString = request.getQueryString();
        StringBuffer sb = new StringBuffer("REQ:");
        sb.append(request.getMethod()).append(",").append(request.getRequestURL().toString());
        if (queryString != null && !queryString.isEmpty()) {
            sb.append("?");
            sb.append(queryString);
        }

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null)
                continue;
            sb.append(",");
            // sb.append(arg.toString());
            sb.append(Utility.objToJsonString(arg));
        }

        log.info(sb.toString());
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        StringBuffer sb = new StringBuffer("RES:");
        sb.append(response.getStatus()).append(",").append(object);

        log.info(sb.toString());
    }

    @AfterThrowing(pointcut = "log()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("Exception:{}", joinPoint.getArgs());
    }

}
