/**
 * 
 */
package hyperitsolutions.ship.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hyperitsolutions.ship.model.entity.Account;

/**
 * @author bruno
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

	
	Account findByAccountName(String accountName);

}
