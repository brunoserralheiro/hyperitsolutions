/**
 * 
 */
package hyperitsolutions.ship.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
public class ShipAPIv1 {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	// TODO Change param from orderName to accountNumber
	/**
	 * POST and GET /ship/{accountNumber} (POST will trigger shipment of all orders
	 * per account ) DELETE and PUT /ship/{accountNumber}/{orderNumber} (idempotent
	 * per order)
	 **/
	@RequestMapping(method = RequestMethod.GET, value = "/{orderName}")
	Order getOrder(@PathVariable String accountName, @PathVariable String orderName) throws OrderNotFoundException {

		this.validateAccount(accountName);
		System.out.println("REST getOrder with name: " + orderName);
		return orderService.findByName(orderName);
	}

	private void validateAccount(String accountName) {
		// TODO Auto-generated method stub

	}

	@RequestMapping(method = RequestMethod.POST, value="/{orderName}")
	ResponseEntity<?> add(@PathVariable String accountName, @PathVariable String orderName, @RequestBody Order input) {
		this.validateAccount(accountName);

		  Account account = accountRepository.findByAccountName(accountName);   
		  
		  		if(null != account && account.getAccountName().equals(accountName)) {
					Order result = orderRepository.save(new Order(account, input.getName(), input.getDescription()));

					URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}")
						.buildAndExpand(result.getId()).toUri();

					return ResponseEntity.created(location).build();
		  		}
		  		else{
		  			return ResponseEntity.noContent().build();	
		  		}

	}

}