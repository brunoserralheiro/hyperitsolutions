package eu.hyperitsolutions.ship;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import hyperitsolutions.ship.ShipApplication;
import hyperitsolutions.ship.model.OrderRepository;
import hyperitsolutions.ship.model.OrderService;
import hyperitsolutions.ship.model.entity.Order;
	
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Order.class})
@ContextConfiguration(classes = ShipApplication.class)
public class ShipOrderTest {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository ;
	
	Order order1;
	
	
	

	@Test
	public void test() {
	
		Order order1 = new Order();
		order1.setName("order1");
		order1 = orderService.save(order1, orderRepository);
		
		Order order2 = new Order();
		order2.setName("order2");
		order2 = orderService.save(order2, orderRepository);
 
		Order testOrder1 = orderService.findByName("order1", orderRepository);
		Order testOrder2 = orderService.findByName("order2", orderRepository);
		assertNotNull(order1);
		assertTrue("order1".equals(testOrder1.getName()));
		assertTrue("order2".equals(testOrder2.getName()));
		System.out.println(orderRepository.findAll().size());
		assertTrue(orderRepository.findAll().size()==3);
		orderRepository.deleteAll();
		
	}

}
