package com.weicl.autopagingcore.result;

/**
 * Result.java是客户端响应实体类。
 *
 * @version 1.0 2017年3月10日
 */
public class Result<T> extends BaseResult {

  private static final long serialVersionUID = 1549544504689739889L;

  /**
   *
   */
  public Result() {
  }

  /**
   *
   * @param code
   * @param msg
   */
  public Result(Integer code,String msg) {
    super.setCode(code);
    super.setMsg(msg);
  }

  /**
   * 响应结果数据
   */
  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
