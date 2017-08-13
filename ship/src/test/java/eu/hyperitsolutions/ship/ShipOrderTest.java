package eu.hyperitsolutions.ship;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hyperitsolutions.ship.model.OrderRepository;
import hyperitsolutions.ship.model.entity.Order;
	
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipOrderTest {

	@Autowired
	private OrderRepository orderRepository;
	
	Order order1;
	

	@Test
	public void test() {
		
		
		Order testOrder = orderRepository.findByName("order1");
		
		assertNotNull(testOrder);
		assertTrue("order1".equals(testOrder.getName()));

	}

}
