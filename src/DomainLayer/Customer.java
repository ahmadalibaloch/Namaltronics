package DomainLayer;

/**
 * Customer domain layer class
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Customer {
	/**
	 * Customer Domain Layer Class for object translation between layers
	 * 
	 */
	public int custId;
	/**
	 * Returns the value of the field called 'custId'.
	 * @return Returns the custId.
	 */
	public int getCustId() {
		return this.custId;
	}
	/**
	 * Sets the field called 'custId' to the given value.
	 * @param custId The custId to set.
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the field called 'name' to the given value.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the value of the field called 'address'.
	 * @return Returns the address.
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * Sets the field called 'address' to the given value.
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Returns the value of the field called 'postcode'.
	 * @return Returns the postcode.
	 */
	public String getPostcode() {
		return this.postcode;
	}
	/**
	 * Sets the field called 'postcode' to the given value.
	 * @param postcode The postcode to set.
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * Returns the value of the field called 'phone'.
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return this.phone;
	}
	/**
	 * Sets the field called 'phone' to the given value.
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Returns the value of the field called 'email'.
	 * @return Returns the email.
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * Sets the field called 'email' to the given value.
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Returns the value of the field called 'cardType'.
	 * @return Returns the cardType.
	 */
	public Payment getPayment() {
		return this.payment;
	}
	/**
	 * Sets the field called 'cardType' to the given value.
	 * @param cardType The cardType to set.
	 */
	public void setPayment(Payment cardType) {
		this.payment = cardType;
	}
	/**
	 * attribute stgring
	 */
	public String name;
	/**
	 * attribute string
	 */
	public String address;
	/**
	 * attribute string
	 */
	public String postcode;
	/**
	 * attribute string
	 */
	public String phone;
	/**
	 * attribute string
	 */
	public String email;
	/**
	 * attribute( class)
	 */
	public Payment payment;
	/**
	 * attribute
	 *
	 */
	public Customer(){
		
	}
	/**
	 * attribute
	 *
	 * @param custId
	 * @param name
	 * @param phone
	 * @param email
	 * @param postcode
	 * @param address
	 * @param payment
	 */
	public Customer(int custId, String name, String phone,String email,String postcode,String address,
			 Payment payment) {
		this.custId = custId;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
		this.payment = payment;
	}

}
