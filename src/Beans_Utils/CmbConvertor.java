package Beans_Utils;

import java.util.LinkedList;

import org.jdesktop.beansbinding.Converter;

import DALayer.Customers;
import DALayer.ItemCategories;
import DALayer.Manufacturers;
import DALayer.Models;
import DALayer.ProblemCategories;
import DomainLayer.Customer;
import DomainLayer.ItemCategory;
import DomainLayer.Manufacturer;
import DomainLayer.Model;
import DomainLayer.ProblemCategory;

/**
 * Conversion class 'beansbinding' from date to strin and vice versa.
 * 
 * @author Ahmad Ali. Created Apr 5, 2012.
 */
public class CmbConvertor extends Converter<Integer, Object> {
	private String converstionType = "Item";

	/**
	 * Gets the string value for defingin the use of this constructor for some category
	 * 
	 * @param converstionType
	 */
	public CmbConvertor(String converstionType) {
		this.converstionType = converstionType;
	}

	@Override
	public Object convertForward(Integer arg0) {
		if (this.converstionType.equalsIgnoreCase("customer")) {
			LinkedList<Customer> custs = Customers.getCustomers("");
			for (Customer c : custs) {
				if (c.getCustId()==arg0) {
					return c.getName();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("model")) {
			LinkedList<Model> models = Models.getModels("");
			for (Model c : models) {
				if (c.getModelId()==arg0) {
					return c.getName();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("manufacturer")) {
			LinkedList<Manufacturer> manus = Manufacturers.getManufacturers("");
			for (Manufacturer m : manus) {
				if (m.getManufacturerId()==arg0) {
					return m.getName();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("itemCategory")) {
			LinkedList<ItemCategory> itemCates = ItemCategories
					.getItemCategories("");
			for (ItemCategory c : itemCates) {
				if (c.itemCateId==arg0) {
					return c.name;
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("problemCategory")) {
			LinkedList<ProblemCategory> ProblemCates = ProblemCategories
					.getProbCategories("");
			for (ProblemCategory p : ProblemCates) {
				if (p.getProbCateId()==arg0){
					System.out.println("category going out "+p.getProbCateId());
					return p.getName();
				}
			}
		}
		return null;
	}

	@Override
	public Integer convertReverse(Object arg0) {

		if (this.converstionType.equalsIgnoreCase("customer")) {
			LinkedList<Customer> custs = Customers.getCustomers("");
			for (Customer c : custs) {
				if (c.getName().equals(arg0)) {
					return c.getCustId();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("model")) {
			LinkedList<Model> models = Models.getModels("");
			for (Model c : models) {
				if (c.getName().equals(arg0)) {
					return c.getModelId();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("manufacturer")) {
			LinkedList<Manufacturer> manus = Manufacturers.getManufacturers("");
			for (Manufacturer m : manus) {
				if (m.getName().equals(arg0)) {
					return m.getManufacturerId();
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("itemCategory")) {
			LinkedList<ItemCategory> itemCates = ItemCategories
					.getItemCategories("");
			for (ItemCategory c : itemCates) {
				if (c.name.equals(arg0)) {
					return c.itemCateId;
				}
			}
		} else if (this.converstionType.equalsIgnoreCase("problemCategory")) {
			LinkedList<ProblemCategory> ProblemCates = ProblemCategories
					.getProbCategories("");
			for (ProblemCategory p : ProblemCates) {
				if (p.getName().equals(arg0)){
					System.out.println("category going out "+p.getProbCateId());
					return p.getProbCateId();
				}
			}
		}
		return null;
	}
}
