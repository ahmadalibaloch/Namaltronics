package DomainLayer;

/**
 * Domain layer class for technician(class) skill
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Skill {
	/**
	 * skill id attribute of skill
	 */
	public int tech_skilId;//this is the id of table skills_techs
	/**
	 * skill category(class) attribute of skill
	 */
	public SkillCategory skillCategory;
	/**
	 * skill level attribute of skill
	 */
	
	public SkillLevel skillLevel;
	/**
	 * skill deteal attribute of skill
	 */
	public String skillDetail;
	
	
	//properties
	/**
	 * Returns the value of the field called 'tech_skilId'.
	 * @return Returns the tech_skilId.
	 */
	public int getTech_skilId() {
		return this.tech_skilId;
	}
	/**
	 * Sets the field called 'tech_skilId' to the given value.
	 * @param tech_skilId The tech_skilId to set.
	 */
	public void setTech_skilId(int tech_skilId) {
		this.tech_skilId = tech_skilId;
	}
	/**
	 * Returns the value of the field called 'skillCategory'.
	 * @return Returns the skillCategory.
	 */
	public SkillCategory getSkillCategory() {
		return this.skillCategory;
	}
	/**
	 * Sets the field called 'skillCategory' to the given value.
	 * @param skillCategory The skillCategory to set.
	 */
	public void setSkillCategory(SkillCategory skillCategory) {
		this.skillCategory = skillCategory;
	}
	/**
	 * Returns the value of the field called 'skillLevel'.
	 * @return Returns the skillLevel.
	 */
	public SkillLevel getSkillLevel() {
		return this.skillLevel;
	}
	/**
	 * Sets the field called 'skillLevel' to the given value.
	 * @param skillLevel The skillLevel to set.
	 */
	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}
	/**
	 * Returns the value of the field called 'skillDetail'.
	 * @return Returns the skillDetail.
	 */
	public String getSkillDetail() {
		return this.skillDetail;
	}
	/**
	 * Sets the field called 'skillDetail' to the given value.
	 * @param skillDetail The skillDetail to set.
	 */
	public void setSkillDetail(String skillDetail) {
		this.skillDetail = skillDetail;
	}
//constructors
	/**
	 * constructor
	 *
	 * @param tech_skilId
	 * @param skillCategory
	 * @param skillLevel 
	 * @param skillDetail 
	 */
	public Skill(int tech_skilId, SkillCategory skillCategory, SkillLevel skillLevel, String skillDetail) {
		this.tech_skilId = tech_skilId;
		this.skillCategory = skillCategory;
		this.skillLevel = skillLevel;
		this.skillDetail=skillDetail;
	}
	 /**
	 * constructor
	 *
	 */
	public Skill(){
		this.skillLevel=SkillLevel.NA;
	}
	
}
