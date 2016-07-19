package DomainLayer;

import DALayer.ProblemCategories;

/**
 * Domain layer attribute class for item
 *
 * @author Administrator.
 *         Created Apr 11, 2012.
 */
public class Problem {
//fields
	private int probId;
	private int probCateId;
	private DifficultyLevel probDifficultyLevel;
	private String probDetail;
	/**
	 * Returns the value of the field called 'probId'.
	 * @return Returns the probId.
	 */
	public int getProbId() {
		return this.probId;
	}
	/**
	 * Sets the field called 'probId' to the given value.
	 * @param probId The probId to set.
	 */
	public void setProbId(int probId) {
		this.probId = probId;
	}
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
	 * Returns the value of the field called 'probDifficultyLevel'.
	 * @return Returns the probDifficultyLevel.
	 */
	public DifficultyLevel getProbDifficultyLevel() {
		return this.probDifficultyLevel;
	}
	/**
	 * Sets the field called 'probDifficultyLevel' to the given value.
	 * @param probDifficultyLevel The probDifficultyLevel to set.
	 */
	public void setProbDifficultyLevel(DifficultyLevel probDifficultyLevel) {
		this.probDifficultyLevel = probDifficultyLevel;
	}
	/**
	 * Returns the value of the field called 'probDetail'.
	 * @return Returns the probDetail.
	 */
	public String getProbDetail() {
		return this.probDetail;
	}
	/**
	 * Sets the field called 'probDetail' to the given value.
	 * @param probDetail The probDetail to set.
	 */
	public void setProbDetail(String probDetail) {
		this.probDetail = probDetail;
	}
	/**
	 * Get the Problem category attribute details for database based on id
	 *
	 * @return problem's category, based on the probCateId field's value.
	 */
	public ProblemCategory getProblemCategory(){
		return ProblemCategories.getProbCategories("probCateId="+this.probCateId).getFirst();
	}
	//constructors
	/**
	 * constructgor
	 *
	 */
	public Problem(){
		this.probDifficultyLevel=DifficultyLevel.NA;
	}
	/**
	 * constructgor
	 *
	 * @param probId
	 * @param probCateId
	 * @param probDifficultyLevel
	 * @param probDetail
	 */
	public Problem(int probId,int probCateId,DifficultyLevel probDifficultyLevel,String probDetail){
		this.probId=probId;
		this.probCateId=probCateId;
		this.probDifficultyLevel=probDifficultyLevel;
		this.probDetail=probDetail;
	}
}
