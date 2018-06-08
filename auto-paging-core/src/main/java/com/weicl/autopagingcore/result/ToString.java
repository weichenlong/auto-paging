package com.weicl.autopagingcore.result;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToString implements Serializable {

  private static final long serialVersionUID = 5550757941254765800L;
  /**
   * 左括号
   */
  protected static final String LEFT_PARENTHESIS = "(";

  /**
   * 右括号
   */
  protected static final String RIGHT_PARENTHESIS = ")";

  /**
   * 分隔符-逗号
   */
  protected static final String COMMA_SEPARATOR = ",";

  /**
   * toString方法重写
   *
   * @see Object#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
