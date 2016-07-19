package DomainLayer;

/**
 * Problem cateogry attribute class for item class
 *
 * @author Administrator.
 *         Created Apr 11, 2012.
 */
public class ProblemCategory {
//fields
	private int probCateId;
	private String name;
	private String description;
//properties
	/**
	 * Returns the value of the field called 'probCateId'.
	 * @return Returns the probCateId.
	 */
	public int getProbCateId() {
		return this.probCateId;
	}
	/**
	 * Sets the field called 'probCateId' to the given value.
	 * @param probCateId The probCateId to set.
	 */
	public void setProbCateId(int probCateId) {
		this.probCateId = probCateId;
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
	 * Returns the value of the field called 'description'.
	 * @return Returns the description.
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Sets the field called 'description' to the given value.
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	//constructors
/**
 * constructgor
 *
 */
	public ProblemCategory(){}
	/**
	 * constructgor
	 *
	 * @param probCateId
	 * @param name
	 * @param description
	 */
	public ProblemCategory(int probCateId,String name,String description){
		this.probCateId=probCateId;
		this.name=name;
		this.description=description;
	}
}
