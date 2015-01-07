package pl.edu.agh.two.abdms.gui;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import pl.edu.agh.two.abdms.classification.ClassifierAgent;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.util.ProcessState;

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
	
	public void execute(ProcessState processState) {
		DataModel dataModel = processState.getDataModel();
		ClassifierAgent agent = new ClassifierAgent(neighboursAmount, dataModel, Lists.newArrayList(choosenColumns), classColumn, learningDataScope);
		processState.setClassificationResults(agent.classify(dataModel.getValues()));
	}
}
