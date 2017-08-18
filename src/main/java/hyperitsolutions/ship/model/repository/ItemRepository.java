/**
 * 
 */
package hyperitsolutions.ship.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hyperitsolutions.ship.model.entity.Item;

/**
 * @author bruno
 *
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

	
	Item findByName(String name);

	List<Item> findByOrders_Id(Long id);
		
		
}
