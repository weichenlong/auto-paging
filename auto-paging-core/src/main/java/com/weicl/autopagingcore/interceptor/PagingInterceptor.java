package com.weicl.autopagingcore.interceptor;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.weicl.autopagingcore.request.BasePageBean;
import com.weicl.autopagingcore.utils.HttpHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 分页拦截器,自动进行分页
 * @author weicl
 */
public class PagingInterceptor extends HandlerInterceptorAdapter implements SelfDefineInterceptorMark {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String json = HttpHelper.getRequestJsonString(request);
    if(StringUtils.isEmpty(json)){
      throw new RuntimeException("处理请求["+request.getRequestURI()+"]时，没有获取到请求参数");
    }
    Gson gson = new Gson();
    BasePageBean basePageBean = gson.fromJson(json, BasePageBean.class);
    int offset = basePageBean.getOffset();
    int pageSize = basePageBean.getLimit() ;
    PageHelper.startPage(offset / pageSize + 1, pageSize);
    return true;
  }

}
