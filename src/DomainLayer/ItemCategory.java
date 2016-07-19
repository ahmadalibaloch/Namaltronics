package DomainLayer;

/**
 * Item Category domain layer attribute class for item
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class ItemCategory {
	/**
	 * item attrbute
	 */
	public int itemCateId;
	/**
	 * item attrbute
	 */
	public String name;
	/**
	 * item attrbute
	 */
	public String storage;
	/**
	 * item attrbute
	 */
	public String description;
	/**
	 * item attrbute
	 *
	 * @param itemCateId
	 * @param name
	 * @param storage 
	 * @param description
	 */
	public ItemCategory(int itemCateId, String name, String storage,String description) {
		this.itemCateId = itemCateId;
		this.name = name;
		this.storage=storage;
		this.description=description;
	}
	/**
	 * coisttucto
	 *
	 */
	public ItemCategory(){}
	
}
