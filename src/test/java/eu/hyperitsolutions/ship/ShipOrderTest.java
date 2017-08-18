package eu.hyperitsolutions.ship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import hyperitsolutions.ship.ShipApplication;
import hyperitsolutions.ship.model.entity.Item;
import hyperitsolutions.ship.model.entity.Order;
import hyperitsolutions.ship.model.repository.ItemRepository;
import hyperitsolutions.ship.model.repository.OrderRepository;
import hyperitsolutions.ship.model.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Order.class })
@ContextConfiguration(classes = ShipApplication.class)
public class ShipOrderTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	Order order1;
	Order order2;

	Item item1;
	Item item2;
	Item item3;

	List<Item> items = new ArrayList<>();

	@Before
	public void setUp() {

		item1 = itemRepository.findByName("item1");
		if (null == item1) {
			item1 = new Item();
			item1.setName("item1");
			item1.setDescription("asdpoijp psdafijnpoaisd psdofjinp");
		}

		item2 = itemRepository.findByName("item2");
		if (null == item2) {
			item2 = new Item();
			item2.setName("item2");
			item2.setDescription("roihuf oeriuhfpsoairfp aosij fpas npoaisd psdofjinp");
		}

		item3 = itemRepository.findByName("item3");
		if (null == item3) {
			item3 = new Item();
			item3.setName("item3");
			item3.setDescription("asdf sdfg sdfg sdpoijp psdafijnpoaisd psdofjinp");
		}

		items.add(item1);
		items.add(item2);
		items.add(item3);

		order1 = orderRepository.findByName("order1");
		if (null == order1) {
			order1 = new Order();
			order1.setName("order1");
			order1.setDescription("a s d p oi jp psdafijnpoaisd psdofjinp");
		}

		order2 = orderRepository.findByName("order2");
		if (null == order2) {
			order2 = new Order();
			order2.setName("order2");
			order2.setDescription("roi jpoisdofi spfdoij asdpoijp psdafi sdvsdajnpoaisd psdofj  inp");
		}
	}

	@Test
	public void testOrder() {

		order1.setItems(items);
		order1 = orderService.save(order1);
		items.remove(1);
		order2.setItems(items);
		order2 = orderService.save(order2);

		Order testOrder1 = orderService.findByName("order1");
		Order testOrder2 = orderService.findByName("order2");
		assertNotNull(order1);
		assertTrue("order1".equals(testOrder1.getName()));
		assertTrue("order2".equals(testOrder2.getName()));
		System.out.println(orderRepository.findAll().size());
		// assertTrue(orderRepository.findAll().size() == 3);
		// orderRepository.deleteAll();

	}

	@Test
	public void testItem() {

		item1 = itemRepository.save(item1);
		item2 = itemRepository.save(item2);
		item3 = itemRepository.save(item3);

		Item testItem1 = itemRepository.findByName("item1");
		Item testItem2 = itemRepository.findByName("item2");
		Item testItem3 = itemRepository.findByName("item3");
		assertTrue("item1".equals(testItem1.getName()));
		assertTrue("item2".equals(testItem2.getName()));
		assertFalse("item1".equals(testItem3.getName()));
	}

	@Test
	public void testOrderItems() {
		order1 = orderRepository.findByName(order1.getName());
		item1 = itemRepository.findByName("item1");
		item3 = itemRepository.findByName("item3");
		List<Item> testItems = new ArrayList<>();
		testItems.add(item1);
		testItems.add(item3);
		order1.setItems(testItems);
		order1 = orderRepository.save(order1);
		assertTrue(orderService.findAllItems(order1.getId()).containsAll(testItems));

	}

	@Test
	public void testSameItemInManyOrders() {
		order2 = orderRepository.findByName(order2.getName());
		List<Item> testItems = new ArrayList<>();
		testItems.add(item1);
		testItems.add(item2);
		testItems.add(item3);
		testItems.add(item1);
		testItems.add(item2);
		testItems.add(item3);
		order2.setItems(testItems);
		order2 = orderService.save(order2);
		assertTrue(orderService.findAllItems(order2.getId()).containsAll(testItems));
	}

}
