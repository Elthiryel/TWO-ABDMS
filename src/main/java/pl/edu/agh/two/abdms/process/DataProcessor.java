package pl.edu.agh.two.abdms.process;


/**
 * Basic element of the process pipeline - node that consumes some kind of
 * data from single data source, and produces single result.
 * 
 * While more general models are obviously conceivable, this simple solution
 * (i.e. basically list of nodes as opposed to actual full-fledged graph)
 * was explicitly requested by the client.
 * 
 * @param <Input> Type of data consumed by the processor
 * @param <Query> Type of requests consumed by the data source used
 * @param <Output> Type of data produced by the processor
 */
public interface DataProcessor<Input, Query, Output> extends Node {

    /**
     * Invoked during process pipeline construction.
     */
    void setSource(DataSource<Query, Input> source);

}
