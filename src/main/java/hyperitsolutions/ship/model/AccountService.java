/**
 * 
 */
package hyperitsolutions.ship.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyperitsolutions.ship.model.entity.Account;

/**
 * @author bruno
 *
 */
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
//	public Optional<Account> findByAccountName(String accountName) {
//
//		return accountRepository.findByAccountName(accountName);
//	}

}
