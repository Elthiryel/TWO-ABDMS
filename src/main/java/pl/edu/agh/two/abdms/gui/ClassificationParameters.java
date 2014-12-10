package pl.edu.agh.two.abdms.gui;

import java.util.Set;

/**
 * Created by pawko on 2014-12-03.
 */
public class ClassificationParameters implements ProcessParameters{


	private Integer neighboursAmount;
	private Set<String> choosenColumns;
	private String classColumn;
	
	public Integer getNeighboursAmount() {
		return neighboursAmount;
	}
	public void setNeighboursAmount(Integer neighboursAmount) {
		this.neighboursAmount = neighboursAmount;
	}
	public Set<String> getChoosenColumns() {
		return choosenColumns;
	}
	public void setChoosenColumns(Set<String> choosenColumns) {
		this.choosenColumns = choosenColumns;
	}
	public String getClassColumn() {
		return classColumn;
	}
	public void setClassColumn(String classColumn) {
		this.classColumn = classColumn;
	}

}
