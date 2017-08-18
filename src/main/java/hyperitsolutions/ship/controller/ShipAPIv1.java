/**
 * 
 */
package hyperitsolutions.ship.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hyperitsolutions.ship.controller.util.OrderRequestWrapper;
import hyperitsolutions.ship.exception.OrderNotFoundException;
import hyperitsolutions.ship.model.AccountRepository;
import hyperitsolutions.ship.model.AccountService;
import hyperitsolutions.ship.model.OrderRepository;
import hyperitsolutions.ship.model.OrderService;
import hyperitsolutions.ship.model.entity.Account;
import hyperitsolutions.ship.model.entity.Order;

/**
 * @author bruno
 *
 */

@RestController
@RequestMapping("/ship/{accountName}")
public class ShipAPIv1 extends AbstractShipAPI {

	@Autowired
	OrderService orderService;

	
	@Autowired
	AccountService accountService;

	// TODO Change param from orderName to accountNumber
	/**
	 * POST and GET /ship/{accountNumber} (POST will trigger shipment of all orders
	 * per account ) DELETE and PUT /ship/{accountNumber}/{orderNumber} (idempotent
	 * per order)
	 **/
	@RequestMapping(method = RequestMethod.GET, value = ORDER_NAME, produces = "application/json")
	ResponseEntity<Order> getOrder(@PathVariable String accountName, @PathVariable String orderName)
			throws OrderNotFoundException {

		this.validateAccount(accountName);
		System.out.println("REST getOrder with name: " + orderName);
		Order result = orderService.findByName(orderName);

		if (null != result) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
					.buildAndExpand(result.getId()).toUri();

			return new ResponseEntity<Order>(result, HttpStatus.OK);
		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = ORDER_NAME, produces = "application/json")
	ResponseEntity<?> add(@PathVariable String accountName, @PathVariable String orderName, @RequestBody OrderRequestWrapper input) {
		

		Account account = this.validateAccount(accountName);

		if (null != account && account.getAccountName().equals(accountName)) {
			Order result = orderService.save(new Order(account, input.getName(), input.getDescription(), input.getItems()));

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
					.buildAndExpand(result.getId()).toUri();

			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.noContent().build();
		}

	}

	private Account validateAccount(String accountName) {
		
		return accountService.findByAccountName(accountName);

	}

}
