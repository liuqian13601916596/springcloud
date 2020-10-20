#        springcoud配置流程

## 一：先创建父工程，maven    加入springboot  相关的依赖，要注意版本对应问题

```
<parent>
       <groupId>org.springframework.boot</groupId>

       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.0.7.RELEASE</version>
       <!--注意与cloud的版本对应的问题-->
   </parent>
     <!--spring boot的父启动包-->



   <dependencies>
       <!--spring boot启动包-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter</artifactId>
           <version>2.0.7.RELEASE</version>
       </dependency>
       <!--健康检查-->
    <!--   <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-actuator</artifactId>
       </dependency>-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <version>1.18.12</version>
       </dependency>
<!--       spring boot测试包-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <scope>test</scope>
       </dependency>
       <!--<dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.13</version>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-jdbc</artifactId>
           <version>2.0.7.RELEASE</version>
       </dependency>-->
    <!--   <dependency>
           <groupId>org.mybatis.spring.boot</groupId>
           <artifactId>mybatis-spring-boot-starter</artifactId>
           <version>2.1.3</version>
       </dependency>-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-thymeleaf</artifactId>
           <version>2.3.3.RELEASE</version>
       </dependency>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-commons</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-commons</artifactId>
       </dependency>
   </dependencies>

   <!--依赖版本管理器-->
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-dependencies</artifactId>
               <version>Finchley.SR2</version>
               <type>pom</type>
               <scope>import</scope>

           </dependency>
       </dependencies>
   </dependencyManagement>
   <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork><!--启动热部署-->
                </configuration>
            </plugin>
        </plugins>

    </build>

```

### 二：在主项目上建moudle,也是建maven工程



### 1注册服务中心eurekaServer

引入springcloud 的注册服务中心的依赖spring-cloud-starter-netflix-eureka-server

```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.0.7.RELEASE</version>
    </dependency>

    <!--再加入springcloud的eureka-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
        <!--再加入健康检查-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    
</dependencies>
```

然后在resources下面创建配置文件 application.yml

```
server:
  port: 8001
  #注册中心
eureka:
  client:
    register-with-eureka: false    #不注册自己  #是否通过其他的注册
    #默认的注册中心的路径
    fetch-registry: false  #是否从注册中心获取信息
    service-url:
      defaultZone: http://localhost:8001/eureka/  #注意要有空格 启动后直接访http://localhost:8001/
    healthcheck:
      enabled: true  #开启健康检查
```

再创建启动类，这样注册中心就完成了

```
@SpringBootApplication
@EnableEurekaServer/*注册中心服务*/
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }

}
```

## 2创建服务提供者eurekaclient

1也是在主项目建maven的子moudle,加入spring-cloud-stater-eureka-client

```
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>

</dependencies>
```

2再写配置文件

```
#服务提供者
spring:
  application:
    name: provider  #当前注册在注册完中心的名字


eureka:
  client:
    service-url:
      defaultZone:  http://localhost:8001/eureka/  #注意要有空格 启动后直接访问http://localhost:8001/
    #是否将当前的服务提供者的Ip地址注册到eureka server中
  instance:
    prefer-ip-address: true
server:
  port: 8887
```

在写启动类

```
/*服务提供者的启动类*/
@SpringBootApplication()
/*@EnableEurekaClient*/
@EnableDiscoveryClient //*加入客户端这个不仅仅是在eureka,也可以是其他的注册中心*/

public class ProviderApplication {
    static Logger logger = LoggerFactory.getLogger(ProviderApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
        logger.info("服务提供者启动");
    }
}
```

主要的项目，entity,service,controller,都在这块写

```
@RestController
public class CustomerHaddler {
    @Resource
    CustomerService customerService;
    @ResponseBody
     @RequestMapping("/json")
    public JsonResult test(){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setMsg("成功");
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/findall")
    public List<Customer> findall(){

        customerService.selAll();

        return  customerService.selAll();
    }

    @RequestMapping("/findname/{id}")
    public Customer findname(@PathVariable("id")  int id) {
        id=1;
     Customer customer =  customerService.selById(id);

        return  customer;
    }
    @Value("${server.port}")
    private  int port;
    @RequestMapping("/port")
    public String selPort(){
        return  "当前的服务端口是"+port;
    }
}
```



要先启动注册中心，再启动服务提供者，这样在eureka的页面才可以看到服务提供者的实例





## 3新建moudel,maven项目服务消费者consumer

1先引入spring-cloud-starter-netflix-eureka-client 依赖

```
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
</dependencies>
```

2 写配置文件

```
server:
  port: 8889
spring:
  application:
    name: consumer #服务消费者
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8001/eureka
  instance:
    prefer-ip-address: true
```

3写启动类

```
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
    @Bean //注入ioc中
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

再写entity,对应的实体类，controller

```
@RestController
public class CustomerHanddler {
    @Resource
    RestTemplate restTemplate;
    @RequestMapping("/find")
    public List<Customer> find(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径是服务提供者里面的访问地址，调用接口*/
        return restTemplate.getForEntity("http://localhost:8888/findall",List.class).getBody();//拿到访问的接口


    }
    @RequestMapping("/find2")
    public List<Customer> find2(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForObject("http://localhost:8888/findall",List.class);//拿到访问的接口


    }
    @RequestMapping("/name/{id}")

    public  Customer findbyname(@PathVariable("id") int id){/*得到对象就不用.getbody（）*/
        System.out.println(restTemplate.getForObject("http://localhost:8888/findname/{id}",Customer.class,id));
        Customer customer=  restTemplate.getForEntity("http://localhost:8888/findname/{id}",Customer.class,id).getBody();
        System.out.println(customer);
        return  customer;//这个方法访问无效，可能是lombok失效，
    }
}
```

## 4新建子项目 ribbon,负载均衡,和服务消费者区别就是启动类和contorller，加一个注释 @LoadBalanced //*负载均衡*/这个的返回值类型得和服务提供的类型一样，下面是访问路径,ribbon访问路径不用ip+port,而是用服务提供者的名称

```
@SpringBootApplication
public class RibbonApplication {
    public static void main(String[] args) {

        SpringApplication.run(RibbonApplication.class,args);
    }

 @LoadBalanced //*负载均衡*/
 @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
```

contorller的访问的地址是服务提供者的名称，不是ip+port

```
@RestController
public class CustomerHanddler {
    @Resource
    RestTemplate restTemplate;
    @RequestMapping("/find")
    public List<Customer> find(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径,ribbon访问路径不用ip+port,而是用服务提供者的名称provider*/
        return restTemplate.getForEntity("http://provider/findall",List.class).getBody();//拿到访问的接口
    }
    @RequestMapping("/find2")
    public List<Customer> find2(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForObject("http://provider/findall",List.class);//拿到访问的接口


    }
    @RequestMapping("/name/{id}")

    public  Customer findbyname(@PathVariable("id") int id){/*得到对象就不用.getbody（）*/
        System.out.println(restTemplate.getForObject("http://provider/findname/{id}",Customer.class,id));
        Customer customer=  restTemplate.getForEntity("http://provider/findname/{id}",Customer.class,id).getBody();
        System.out.println(customer);
        return  customer;
    }
}
```

## 5建子项目restTemplate，就是调用接口，不必注册到注册中心，因为父工程有springboot的依赖，不用依赖了，就是建entity和contorller,启动类

```java
@RestController
public class CustomerHanddler {
    @Resource
    RestTemplate restTemplate;
    @RequestMapping("/find")
    public List<Customer> find(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForEntity("http://localhost:8888/findall",List.class).getBody();//拿到访问的接口


    }
    @RequestMapping("/find2")
    public List<Customer> find2(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForObject("http://localhost:8888/findall",List.class);//拿到访问的接口


    }
    @RequestMapping("/name/{id}")

    public  Customer findbyname(@PathVariable("id") int id){/*得到对象就不用.getbody（）*/
        System.out.println(restTemplate.getForObject("http://localhost:8888/findname/{id}",Customer.class,id));
        Customer customer=  restTemplate.getForEntity("http://localhost:8888/findname/{id}",Customer.class,id).getBody();
        System.out.println(customer);
        return  customer;
    }
}
```

```java
@SpringBootApplication
public class RestTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestTemplateApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
```

## 6建子项目，网关zuul,加入依赖

```
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

写配置文件

```java
server:
  port: 8890

spring:
  application:
    name: gateway #服务网关名
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8001/eureka
zuul:
  routes:
    provider:   /p/**             #服务提供者的name 的别名 在浏览器访问方式http://localhost:8890/p/findall
```

写启动类

```java
@EnableZuulProxy /*生成网关代理 包含了EnableZuulServer*/
@EnableAutoConfiguration /*自动配置,将这个配置加入到ioc*/
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
```

