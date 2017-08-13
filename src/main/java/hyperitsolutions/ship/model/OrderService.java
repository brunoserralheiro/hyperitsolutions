/**
 * 
 */
package hyperitsolutions.ship.model;

import org.springframework.stereotype.Service;

import hyperitsolutions.ship.model.entity.Order;

/**
 * @author bruno
 *
 */
@Service
public class OrderService {

	
	
	public Order findByName(String name , OrderRepository dao) {

		return dao.findByName(name);
	}
	
	public Order save(Order order, OrderRepository dao) {
		return dao.save(order);	
	}

}
