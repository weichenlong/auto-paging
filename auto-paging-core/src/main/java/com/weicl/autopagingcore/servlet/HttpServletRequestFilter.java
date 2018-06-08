package com.weicl.autopagingcore.servlet;


import com.weicl.autopagingcore.interceptor.SelfDefineInterceptorMark;
import com.weicl.autopagingcore.utils.ApplicationContextUtil;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.util.UrlPathHelper;

/**
 * Http请求过滤器，拦截到请求，首先判断是否是静态资源，是的话,直接过滤掉,
 * 否则,查看分页拦截器配置(在spring-mvc.xml中配置分页拦截器),是否匹配请求路径
 * ，不匹配的话，也过滤掉，匹配的话,判断请求是否是"POST"请求，是的话,装换为BodyHttpServletRequestWrapper
 * 否则,过滤掉
 * @author weicl
 */
public class HttpServletRequestFilter implements Filter {

  private ApplicationContext applicationContext;

  private UrlPathHelper urlPathHelper = new UrlPathHelper();

  private PathMatcher pathMatcher = new AntPathMatcher();

  private final String regex = "/((\\w+)|(\\w+\\.(do|html|action|paging)))";

  private Collection<MappedInterceptor> mappedInterceptors;


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletRequest requestWrapper = null;
    if(request instanceof HttpServletRequest){
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      String servletPath = httpServletRequest.getServletPath();
      servletPath = servletPath.substring(servletPath.lastIndexOf("/"));

      if(servletPath.matches(regex)){
        if(applicationContext == null){
          applicationContext = ApplicationContextUtil.getContext();
        }
        if(mappedInterceptors == null){
          mappedInterceptors = BeanFactoryUtils.beansOfTypeIncludingAncestors(
              applicationContext, MappedInterceptor.class, true, false).values();
        }
        String lookupPath = this.urlPathHelper.getLookupPathForRequest(httpServletRequest);
        for(MappedInterceptor mappedInterceptor : mappedInterceptors){
          HandlerInterceptor handlerInterceptor = mappedInterceptor.getInterceptor();
          if(SelfDefineInterceptorMark.class.isAssignableFrom(handlerInterceptor.getClass()) &&
              mappedInterceptor.matches(lookupPath,pathMatcher)){
            if("POST".equals(httpServletRequest.getMethod().toUpperCase())){
              requestWrapper = new BodyHttpServletRequestWrapper(httpServletRequest);
              break;
            }
          }
        }
      }
    }
    if(requestWrapper == null){
      chain.doFilter(request,response);
    }else{
      chain.doFilter(requestWrapper,response);
    }
  }

  @Override
  public void destroy() {

  }

}
