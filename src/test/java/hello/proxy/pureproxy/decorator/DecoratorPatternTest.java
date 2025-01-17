package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
         Component realComponent = new RealComponent();
         DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
         client.execute();
    }

    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);
        client.execute();
    }

    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
