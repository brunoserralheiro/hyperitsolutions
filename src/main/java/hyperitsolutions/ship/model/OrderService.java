/**
 * 
 */
package hyperitsolutions.ship.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyperitsolutions.ship.model.entity.Item;
import hyperitsolutions.ship.model.entity.Order;

/**
 * @author bruno
 *
 */
@Service
public class OrderService {

	@Autowired
	OrderRepository orderDao;
	
	@Autowired
	ItemRepository itemDao;
	
	public Order findByName(String name ) {

		return orderDao.findByName(name);
	}
	
	public Order save(Order order ) {
		return orderDao.save(order);	
	}

	public List<Item> findAllItems(Long id) {
		
		return itemDao.findByOrders_Id(id);
	}

}
