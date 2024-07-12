package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        // call() 호출 시 handler의 invoke가 무조건 실행 됨, invoke의 method에는 call()을 넘겨줌
        proxy.call();
        log.info("targetClass = {}",target.getClass());
        log.info("proxyClass = {}",proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        // call() 호출 시 handler의 invoke가 무조건 실행 됨, invoke의 method에는 call()을 넘겨줌
        proxy.call();
        log.info("targetClass = {}",target.getClass());
        log.info("proxyClass = {}",proxy.getClass());
    }
}
