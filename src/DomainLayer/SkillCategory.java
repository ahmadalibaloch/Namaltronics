package DomainLayer;

/**
 * Skill cAtegory to be mettioned in skill of technician
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class SkillCategory {
	/**
	 * attribute of skill cateogry
	 */
	public int skillCateId;
	/**
	 * attribute of skill cateogry
	 */
	public String name;
	/**
	 * attribute of skill cateogry
	 */
	public String description;
	/**
	 * attribute of skill cateogry
	 *
	 * @param skillCateId
	 * @param name
	 * @param description
	 */
	public SkillCategory(int skillCateId, String name, String description) {
		this.skillCateId = skillCateId;
		this.name = name;
		this.description=description;
	}
	/**
	 * constructor
	 *
	 */
	public SkillCategory(){}
	
}
