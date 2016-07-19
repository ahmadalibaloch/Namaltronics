package DomainLayer;

/**
 * Domain layer class for manufacturer
 *
 * @author Administrator.
 *         Created Apr 11, 2012.
 */
public class Manufacturer {
	private int manufacturerId;
	private String name;
	private String website;
	private String email;
	/**
	 * Returns the value of the field called 'modelId'.
	 * @return Returns the modelId.
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * Sets the field called 'modelId' to the given value.
	 * @param email The modelId to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Returns the value of the field called 'modelName'.
	 * @return Returns the modelName.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the field called 'modelName' to the given value.
	 * @param name The modelName to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the value of the field called 'year'.
	 * @return Returns the year.
	 */
	public String getWebsite() {
		return this.website;
	}
	/**
	 * Sets the field called 'year' to the given value.
	 * @param website The year to set.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * Sets the field called 'manufacturerId' to the given value.
	 * @param manufacturerId The manufacturerId to set.
	 */
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	/**
	 * Sets the field called 'manufacturerId' to the given value.
	 * @return id
	 */
	public int getManufacturerId() {
		return this.manufacturerId;
	}
	/**
	 * contstorugt
	 *
	 */
	public Manufacturer(){}
	/**
	 * constructor
	 *
	 * @param manufacturerId
	 * @param name
	 * @param website
	 * @param email
	 */
	public Manufacturer(int manufacturerId, String name, String website,
			String email) {
		super();
		this.manufacturerId = manufacturerId;
		this.name = name;
		this.website = website;
		this.email = email;
	}
	

}
