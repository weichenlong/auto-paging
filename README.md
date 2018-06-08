# auto-paging 自动分页
自动分页功能可以方便开发人员开发，减少代码冗余，使开发人员只关注于业务，不用在关注与分页。

# auto-pagin 分页用法
该分页功能用起来很简单，只需要在spring配置文件中配置要分页的请求或者在请求后面
追加“.paging”就可以。配置如下：

  <!--注册拦截器-->
  <mvc:interceptors>
    <mvc:interceptor>
      <!--指定分页拦截器要拦截的请求-->
      <mvc:mapping path="/xx/xx"/>
      <mvc:mapping path="/**/*.paging"/>
      <!--分页拦截器-->
      <bean class="com.weicl.autopagingcore.interceptor.PagingInterceptor"/>
    </mvc:interceptor>
   </mvc:interceptors>
# auto-paging 依赖环境
自动分页，依赖spring,springmvc,mybaits,pagehelper
