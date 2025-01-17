package hello.proxy.app.v3;

import hello.proxy.app.v2.OrderRepositoryV2;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {
    private final OrderRepositoryV3 repository;
    public OrderServiceV3(OrderRepositoryV3 repository) {
        this.repository = repository;
    }

    public void orderItem(String itemId) {
        repository.save(itemId);
    }
}
