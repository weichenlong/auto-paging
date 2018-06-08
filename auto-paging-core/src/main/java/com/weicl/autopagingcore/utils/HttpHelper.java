package com.weicl.autopagingcore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http帮助类
 * @author weicl
 */
public class HttpHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

  /**
   * 获取请求参数json字符串
   * @param request
   * @return String
   */
  public static String getRequestJsonString(HttpServletRequest request){
    StringBuffer jsonBuffer = new StringBuffer(10);
    BufferedReader reader = null;
    InputStream inputStream = null;
    try {
      inputStream = request.getInputStream();
      reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
      String line = null;
      while((line = reader.readLine()) != null){
        jsonBuffer.append(line);
      }
    } catch (IOException e) {
      throw new RuntimeException("处理请求["+request.getRequestURI()+"]时，获取请求参数出错",e);
    } finally {
      if(reader != null){
        try {
          inputStream.close();
          reader.close();
        } catch (IOException e) {
          LOGGER.error(e.getMessage(),e);
        }
      }
    }
    return jsonBuffer.toString();
  }
}
