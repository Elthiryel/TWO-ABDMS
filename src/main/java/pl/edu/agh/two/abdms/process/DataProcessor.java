package pl.edu.agh.two.abdms.process;


public interface DataProcessor<Input, Query, Output> extends Node {

    void setSource(DataSource<Query, Input> source);

    DataSource<?, Input> getSource();

}
