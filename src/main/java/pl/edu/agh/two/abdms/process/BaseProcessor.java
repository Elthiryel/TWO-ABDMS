package pl.edu.agh.two.abdms.process;

/**
 * Base class to reduce boilerplate necessary to implement DataProcessor.
 */
public class BaseProcessor<Input, Query, Output> extends BaseNode
        implements DataProcessor<Input, Query, Output> {

    protected DataSource<Query, Input> source;

    @Override
    public void setSource(DataSource<Query, Input> source) {
        this.source = source;
    }

}
