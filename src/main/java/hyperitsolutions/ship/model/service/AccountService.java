/**
 * 
 */
package hyperitsolutions.ship.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyperitsolutions.ship.model.entity.Account;
import hyperitsolutions.ship.model.repository.AccountRepository;

/**
 * @author bruno
 *
 */
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public Account findByAccountName(String accountName) {

		return accountRepository.findByAccountName(accountName);
	}

}
