/**
 * 
 */
package hyperitsolutions.ship.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hyperitsolutions.ship.model.entity.Item;
import hyperitsolutions.ship.model.entity.Order;
import hyperitsolutions.ship.model.repository.ItemRepository;
import hyperitsolutions.ship.model.repository.OrderRepository;

/**
 * @author bruno
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderDao;

	@Autowired
	ItemRepository itemDao;
	
	@PersistenceContext
	EntityManager entityManager;

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
