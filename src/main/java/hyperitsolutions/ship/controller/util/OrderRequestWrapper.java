/**
 * 
 */
package hyperitsolutions.ship.controller.util;

import java.util.List;

import hyperitsolutions.ship.model.entity.Account;
import hyperitsolutions.ship.model.entity.Item;

/**
 * @author bruno
 *
 */
public class OrderRequestWrapper {
	
	/*
	 {
	"description": "descblablabla",
	"name": "firstPostTestOrder",
	"items": 
	[
		{
			"id": 1,
			"name": "Zeon PRocessor E123XX456",
			"description": "Test Item blablabla"
		}
	]
}
	 */
	
	private String description;
	
	private String name;
	
	private List<Item> items;
	
	private Account account;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
