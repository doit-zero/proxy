package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*","order*","save*"};
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1 controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},new LogTraceFilterHandler(controllerImpl,logTrace,PATTERNS));
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace){
        OrderServiceV1 serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},new LogTraceFilterHandler(serviceImpl,logTrace,PATTERNS));
        return proxy;
    }
    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace){
        OrderRepositoryV1 repositoryImpl = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(repositoryImpl,logTrace,PATTERNS));
        return proxy;
    }
}
