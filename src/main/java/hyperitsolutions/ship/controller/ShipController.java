/**
 * 
 */
package hyperitsolutions.ship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hyperitsolutions.ship.exception.OrderNotFoundException;
import hyperitsolutions.ship.model.OrderRepository;
import hyperitsolutions.ship.model.OrderService;
import hyperitsolutions.ship.model.entity.Order;

/**
 * @author bruno
 *
 */

@RestController
@RequestMapping("/ship")
public class ShipController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired 
	OrderRepository orderRepository; 
	
	
	//TODO Change param from orderName to accountNumber
	/** POST and GET    /ship/{accountNumber}               (POST will trigger shipment of all orders per account )
	 *  DELETE and PUT  /ship/{accountNumber}/{orderNumber} (idempotent per order)
	**/
	@RequestMapping( method= RequestMethod.GET, value="/{orderName}")
	Order getOrder( @PathVariable String orderName) throws OrderNotFoundException{
		System.out.println("REST getOrder with name: "+orderName);
		return orderService.findByName(orderName);
	}

}
