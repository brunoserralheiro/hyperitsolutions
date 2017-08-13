/**
 * 
 */
package hyperitsolutions.ship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bruno
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	public  Order findByName(String name);

	
	
}
