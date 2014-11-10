package pl.edu.agh.two.abdms.process;

public interface DataSource<Query, Result> extends Node {
    
    Result getData(Query query);
    
}
