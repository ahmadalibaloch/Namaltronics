package DomainLayer;

/**
 * Domain layer class for model of item
 *
 * @author Administrator.
 *         Created Apr 11, 2012.
 */
public class Model {
	private int modelId;
	private String name;
	private int year;
	private int manufacturerId;
	/**
	 * Returns the value of the field called 'modelId'.
	 * @return Returns the modelId.
	 */
	public int getModelId() {
		return this.modelId;
	}
	/**
	 * Sets the field called 'modelId' to the given value.
	 * @param modelId The modelId to set.
	 */
	public void setModelId(int modelId) {
		this.modelId = modelId;
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
	 * @param modelName The modelName to set.
	 */
	public void setName(String modelName) {
		this.name = modelName;
	}
	/**
	 * Returns the value of the field called 'year'.
	 * @return Returns the year.
	 */
	public int getYear() {
		return this.year;
	}
	/**
	 * Sets the field called 'year' to the given value.
	 * @param year The year to set.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Returns the value of the field called 'manufacturerId'.
	 * @return Returns the manufacturerId.
	 */
	public int getManufacturerId() {
		return this.manufacturerId;
	}
	/**
	 * Sets the field called 'manufacturerId' to the given value.
	 * @param manufacturerId The manufacturerId to set.
	 */
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	/**
	 * constrcutor
	 *
	 * @param modelId
	 * @param modelName
	 * @param year
	 * @param manufacturerId
	 */
	public Model(int modelId, String modelName, int year, int manufacturerId) {
		this.modelId = modelId;
		this.name = modelName;
		this.year = year;
		this.manufacturerId = manufacturerId;
	}
	/**
	 * cosntrucot
	 *
	 */
	public Model(){}
	

}
