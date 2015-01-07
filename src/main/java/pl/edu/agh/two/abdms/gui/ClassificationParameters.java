package pl.edu.agh.two.abdms.gui;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by pawko on 2014-12-03.
 */
public class ClassificationParameters implements ProcessParameters{


	private Integer neighboursAmount = 1;
	private Double learningDataScope = 0.5;
	private Collection<String> choosenColumns = Collections.emptyList();
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
