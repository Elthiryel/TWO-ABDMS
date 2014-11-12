package pl.edu.agh.two.abdms.process;


/**
 * Data source is "database" abstraction - given some request/query, it
 * returns data.
 * 
 * @param <Query> Type of source's query parameter
 * @param <Result> Data provided by the source
 */
public interface DataSource<Query, Result> extends Node {
    
    Result getData(Query query);
    
}
