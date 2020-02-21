# gzz-cloud
Springboot-cloud 全家桶 学习
## 生产者 member-service

## 消费者 order-service
### 调用Member生产者的接口
- RestTemplate + Ribbon 方式  调用Member生产者的接口
- RestTemplate + Ribbon 方式  调用Member生产者的接口



## 熔断

### 隔离机制THREAD和SEMAPHORE

`@HystrixCommand`有两种隔离机制,THREAD(默认,推荐)和SEMAPHORE

### THREAD

顾名思义,thread隔离机制,跟线程池相关,Hystrix的线程池配置如下:

线程数默认值10,适用于大部分情况（有时可以设置得更小），如果需要设置得更大，那有个基本得公式可以follow：
requests per second at peak when healthy × 99th percentile latency in seconds + some breathing room
每秒最大支撑的请求数 (99%平均响应时间 + 缓存值)
比如：每秒能处理1000个请求，99%的请求响应时间是60ms，那么公式是：
1000 （0.060+0.012）

基本得原则时保持线程池尽可能小，他主要是为了释放压力，防止资源被阻塞。
当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务

```powershell
hystrix.threadpool.default.coreSize 并发执行的最大线程数，默认10
hystrix.threadpool.default.maxQueueSize BlockingQueue的最大队列数，当设为－1，会使用SynchronousQueue，值为正时使用LinkedBlcokingQueue。该设置只会在初始化时有效，之后不能修改threadpool的queue size，除非reinitialising thread executor。默认－1。
hystrix.threadpool.default.queueSizeRejectionThreshold 即使maxQueueSize没有达到，达到queueSizeRejectionThreshold该值后，请求也会被拒绝。因为maxQueueSize不能被动态修改，这个参数将允许我们动态设置该值。if maxQueueSize == -1，该字段将不起作用
hystrix.threadpool.default.keepAliveTimeMinutes 如果corePoolSize和maxPoolSize设成一样（默认实现）该设置无效。如果通过plugin（https://github.com/Netflix/Hystrix/wiki/Plugins）使用自定义实现，该设置才有用，默认1.
hystrix.threadpool.default.metrics.rollingStats.timeInMilliseconds 线程池统计指标的时间，默认10000
hystrix.threadpool.default.metrics.rollingStats.numBuckets 将rolling window划分为n个buckets，默认10
```



~~~java

//name一般都是上面一串中default后面的
@HystrixCommand(fallbackMethod = "hiError"
           ,threadPoolProperties = {
           @HystrixProperty(name = "coreSize",value = "100")
       }
    )

~~~

### SEMAPHORE

Execution相关的属性的配置：
hystrix.command.default.execution.isolation.strategy 隔离策略，默认是Thread, 可选Thread｜Semaphore
thread 通过线程数量来限制并发请求数，可以提供额外的保护，但有一定的延迟。一般用于网络调用
semaphore 通过semaphore count来限制并发请求数，适用于无网络的高并发请求
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds 命令执行超时时间，默认1000ms
hystrix.command.default.execution.timeout.enabled 执行是否启用超时，默认启用true
hystrix.command.default.execution.isolation.thread.interruptOnTimeout 发生超时是是否中断，默认true
hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests 最大并发请求数，默认10，该参数当使用ExecutionIsolationStrategy.SEMAPHORE策略时才有效。

 如果达到最大并发请求数，请求会被拒绝。理论上选择semaphore size的原则和选择thread size一致，但选用semaphore时每次执行的单元要比较小且执行速度快（ms级别），否则的话应该用thread。
semaphore应该占整个容器（tomcat）的线程池的一小部分。

~~~java
  @HystrixCommand(fallbackMethod = "hiError"
            , commandProperties = {
            //隔离策略,默认推荐是THREAD,还有就是SEMAPHORE(信号)
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            //熔断的最大并发请求,只有在隔离策略是SEMAPHORE时才生效
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1000")
            }
    )

~~~

**SpringCloud 工具框架：**

~~~powershell
1、Spring Cloud Config 配置中心，利用git集中管理程序的配置。 
2、Spring Cloud Netflix 集成众多Netflix的开源软件
3、Spring Cloud Bus 消息总线，利用分布式消息将服务和服务实例连接在一起，用于在一个集群中传播状态的变化 
4、Spring Cloud for Cloud Foundry 利用Pivotal Cloudfoundry集成你的应用程序
5、Spring Cloud Cloud Foundry Service Broker 为建立管理云托管服务的服务代理提供了一个起点。
6、Spring Cloud Cluster 基于Zookeeper, Redis, Hazelcast, Consul实现的领导选举和平民状态模式的抽象和实现。
7、Spring Cloud Consul 基于Hashicorp Consul实现的服务发现和配置管理。
8、Spring Cloud Security 在Zuul代理中为OAuth2 rest客户端和认证头转发提供负载均衡
9、Spring Cloud Sleuth SpringCloud应用的分布式追踪系统，和Zipkin，HTrace，ELK兼容。
10、Spring Cloud Data Flow 一个云本地程序和操作模型，组成数据微服务在一个结构化的平台上。
11、Spring Cloud Stream 基于Redis,Rabbit,Kafka实现的消息微服务，简单声明模型用以在Spring Cloud应用中收发
息。
12、Spring Cloud Stream App Starters 基于Spring Boot为外部系统提供spring的集成
13、Spring Cloud Task 短生命周期的微服务，为SpringBooot应用简单声明添加功能和非功能特性。
14、Spring Cloud Task App Starters
15、Spring Cloud Zookeeper 服务发现和配置管理基于Apache Zookeeper。
16、Spring Cloud for Amazon Web Services 快速和亚马逊网络服务集成。
17、Spring Cloud Connectors 便于PaaS应用在各种平台上连接到后端像数据库和消息经纪服务。
18、Spring Cloud Starters （项目已经终止并且在Angel.SR2后的版本和其他项目合并）
19、Spring Cloud CLI 插件用Groovy快速的创建Spring Cloud组件应用。

~~~



**SpringCloud 与 dubbo**

~~~
Dubbo，是阿里巴巴服务化治理的核心框架，并被广泛应用于中国各互联网公司；

Spring Cloud是大名鼎鼎的Spring家族的产品；

而dubbo曾经确实很牛逼，但是Spring Cloud是站在近些年技术发展之上进行开发，因此更具技术代表性
~~~

