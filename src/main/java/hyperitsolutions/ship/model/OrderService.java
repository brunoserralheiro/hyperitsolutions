/**
 * 
 */
package hyperitsolutions.ship.model;

import java.util.ArrayList;
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

	public Order findByName(String name) {

		return orderDao.findByName(name);
	}

	public Order save(Order order) {
		List<Item> items = new ArrayList<>();
		order.getItems().stream().forEach(i -> {
			items.add(itemDao.findByName(i.getName()));
		});
		order.setItems(items);
		return orderDao.save(order);
	}

	public List<Item> findAllItems(Long id) {

		return itemDao.findByOrders_Id(id);
	}

}
