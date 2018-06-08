package com.weicl.autopagingcore.interceptor;

import com.github.pagehelper.PageInfo;
import com.weicl.autopagingcore.result.Result;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 分页响应值拦截器
 * 如果返回的数据本身是分页的数据,则不做处理;
 * 如果是List，则转换为PageInfo，并作为Result 的数据返回
 * @author weicl
 */
//@Order(1)
public class ResponseBodyInterceptor implements ResponseBodyAdvice<Result> {

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return GsonHttpMessageConverter.class.isAssignableFrom(converterType);
  }

  @Override
  public Result beforeBodyWrite(Result body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    Object obj = body.getData();
    if(obj == null){
      return body;
    }
    if(List.class.isAssignableFrom(obj.getClass())){//把集合转换成分页
      body.setData(new PageInfo((List)obj));
    }
    return body;
  }
}
