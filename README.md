# springcloud
学习springcloud组件
1.先启动注册中心，再启动配置中心，再启动服务提供者
2：配置中心的本地读取文件 用  order-dev.yml 命名
3读取配置文件的命名用bootstrap.yml
  cloud:
    config:
      uri: http://localhost:8002 #访问配置中心 #配置中心要读取的文件
      fail-fast: true
      name: order

  profiles:
    active:  dev #-的后缀
