/**
 * 
 */
package hyperitsolutions.ship.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hyperitsolutions.ship.model.entity.Order;

/**
 * @author bruno
 *
 */
//@Repository
//@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

	public  Order findByName(String name);

	
	
}
