package com.weicl.autopagingcore.result;


/**
 * BaseResult.java是客户端响应实体基类。
 *
 * @version 1.0 2017年3月10日
 */
public class BaseResult extends ToString {

  private static final long serialVersionUID = -2790341625134781815L;
  /**
   * 版本号
   */
  private String version = "1.0";

  /**
   * 响应码
   */
  private Integer code;

  /**
   * 响应提示信息
   */
  private String msg;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
