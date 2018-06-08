package com.weicl.autopagingcore.servlet;


import com.weicl.autopagingcore.utils.HttpHelper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 初始化读取request的数据,并把数据存储到bodyBytes数组里,
 * 用以保证请求数据能够多次被读取
 * @author weicl
 */
public class BodyHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private final byte[] bodyBytes;

  /**
   * Constructs a request object wrapping the given request.
   *
   */
  public BodyHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
    bodyBytes = HttpHelper.getRequestJsonString(request).getBytes(Charset.forName("UTF-8"));
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream bis = new ByteArrayInputStream(bodyBytes);
    return new ServletInputStream() {

      public boolean isFinished() {
        return false;
      }

      public boolean isReady() {
        return false;
      }

      public void setReadListener(ReadListener readListener) {

      }

      @Override
      public int read() throws IOException {
        return bis.read();
      }
    };
  }
}
