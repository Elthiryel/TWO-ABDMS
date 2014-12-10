package pl.edu.agh.two.abdms.gui;

import java.util.Collection;

/**
 * Created by pawko on 2014-12-03.
 */
public class ClassificationParameters implements ProcessParameters{


	private Integer neighboursAmount;
	private Double learningDataScope;
	private Collection<String> choosenColumns;
	private String classColumn;
	
	public Double getLearningDataScope() {
		return learningDataScope;
	}
	public void setLearningDataScope(Double learningDataScope) {
		this.learningDataScope = learningDataScope;
	}
	public Integer getNeighboursAmount() {
		return neighboursAmount;
	}
	public void setNeighboursAmount(Integer neighboursAmount) {
		this.neighboursAmount = neighboursAmount;
	}
	public Collection<String> getChoosenColumns() {
		return choosenColumns;
	}
	public void setChoosenColumns(Collection<String> choosenColumns) {
		this.choosenColumns = choosenColumns;
	}
	public String getClassColumn() {
		return classColumn;
	}
	public void setClassColumn(String classColumn) {
		this.classColumn = classColumn;
	}

}
