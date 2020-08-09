package pl.dmcs.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.order.service.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
