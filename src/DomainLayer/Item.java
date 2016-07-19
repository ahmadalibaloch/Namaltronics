package DomainLayer;

import java.util.LinkedList;

import DALayer.Customers;
import DALayer.ItemCategories;
import DALayer.Jobs;
import DALayer.Manufacturers;
import DALayer.Models;

/**
 * domain layer item class for the repair
 *
 * @author Ahmad Ali.
 *         Created March 16, 2012.
 */
public class Item {
	private int itemId;
	private String name;
	private String desc;
	private Problem prob;
	private int custId;
	private int itemCateId;
	private int manuId;
	private int modeId;

	//
	/**
	 * constructor
	 *
	 * @param itemId
	 * @param name
	 * @param desc
	 * @param itemCateId 
	 * @param prob 
	 * @param custId 
	 * @param manuId 
	 * @param modeId 
	 */
	public Item(int itemId, String name,String desc,int itemCateId,	Problem prob, int custId,int manuId,int modeId) {
		this.itemId = itemId;
		this.name = name;
		this.desc = desc;
		this.prob = prob;
		this.custId = custId;
		this.itemCateId=itemCateId;
		this.manuId=manuId;
		this.modeId=modeId;
	}


	/**
	 * constructor
	 *
	 */
	public Item() {
		this.prob=new Problem();
	}
	//
	/**
	 * Returns the value of the field called 'itemId'.
	 * @return Returns the itemId.
	 */
	public int getItemId() {
		return this.itemId;
	}


	/**
	 * Sets the field called 'itemId' to the given value.
	 * @param itemId The itemId to set.
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	 * Returns the value of the field called 'desc'.
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return this.desc;
	}


	/**
	 * Sets the field called 'desc' to the given value.
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


	/**
	 * Returns the value of the field called 'probId'.
	 * @return Returns the probId.
	 */
	public Problem getProblem() {
		return this.prob;
	}


	/**
	 * Sets the field called 'probId' to the given value.
	 * @param prob The probId to set.
	 */
	public void setProbId(Problem prob) {
		this.prob = prob;
	}


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
	 * Returns the value of the field called 'itemCateId'.
	 * @return Returns the itemCateId.
	 */
	public int getItemCateId() {
		return this.itemCateId;
	}


	/**
	 * Sets the field called 'itemCateId' to the given value.
	 * @param itemCateId The itemCateId to set.
	 */
	public void setItemCateId(int itemCateId) {
		this.itemCateId = itemCateId;
	}


	/**
	 * Returns the value of the field called 'manuId'.
	 * @return Returns the manuId.
	 */
	public int getManuId() {
		return this.manuId;
	}


	/**
	 * Sets the field called 'manuId' to the given value.
	 * @param manuId The manuId to set.
	 */
	public void setManuId(int manuId) {
		this.manuId = manuId;
	}


	/**
	 * Returns the value of the field called 'modeId'.
	 * @return Returns the modeId.
	 */
	public int getModeId() {
		return this.modeId;
	}


	/**
	 * Sets the field called 'modeId' to the given value.
	 * @param modeId The modeId to set.
	 */
	public void setModeId(int modeId) {
		this.modeId = modeId;
	}
	//properties
	/**
	 * get the customer of current item bases in id
	 *
	 * @return Customer of the item.
	 */
	public Customer getCustomer(){
		return Customers.getCustomers("custId="+this.custId).getFirst();
	}
	/**
	 * get manufactur bases on id
	 *
	 * @return item manufacturer
	 */
	public Manufacturer getManufacturer(){
		return Manufacturers.getManufacturers("manuId="+this.manuId).getFirst();
	}
	/**
	 * get model based on id
	 *
	 * @return Item model
	 */
	public Model getModel(){
		return Models.getModels("modeId="+this.getModeId()).getFirst();
	}
	/**
	 * get item category based on id
	 *
	 * @return Item Category bases on id stored.
	 */
	public ItemCategory getItemCategory(){
		return ItemCategories.getItemCategories("itemCateId="+this.itemCateId).getFirst();
	}
	/**
	 * get technician assiged to this item. based on if. or nul
	 *
	 * @return technician to whome this item is assigned for solution.
	 */
	public User getItemTechnician(){
		LinkedList<Job> jobs = null;
		if((jobs = Jobs.getJobs("itemId="+this.itemId)).size() > 0)
			return jobs.getFirst().getTech();
		return null;
	}
	//
	@Override
	public String toString(){
		return this.name;
	}
	
}
