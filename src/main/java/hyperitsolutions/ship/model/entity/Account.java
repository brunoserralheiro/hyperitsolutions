/**
 * 
 */
package hyperitsolutions.ship.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author bruno
 *
 */
@Entity
@Table(name="bms_account")
public class Account implements Serializable {

	private static final long serialVersionUID = -7111389666965243620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Long id;
	
	
	@Column(name="account_name", nullable = false, unique = true)
	private String accountName;
	
	@JsonIgnore
	@Column(name="account_password", nullable = false)
	private String accountPassword;
	
	/**
	 * 
	 */
	public Account() {
		super();
	}

	public Account(Long id, String accountName) {
		super();
		this.id = id;
		this.accountName = accountName;
	}
	
	public Account(Long id, String accountName, String accountPassword) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
	}


	@OneToMany(mappedBy = "account")	
	List<Order> orders;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public String getAccountPassword() {
		return accountPassword;
	}



	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}



	public List<Order> getOrders() {
		return orders;
	}



	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (accountName == null) {
			if (other.accountName != null) {
				return false;
			}
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
}
