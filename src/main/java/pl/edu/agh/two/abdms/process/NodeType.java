package pl.edu.agh.two.abdms.process;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to mark Node implementations and provide metadata necessary to
 * use it.
 * 
 * @see Node
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeType {
    
    /** 
     * Name of type of node marked class implements 
     */
    String typeName();
    
    /** 
     * Type of config object the class' constructor expects
     * 
     * @see NodeConfig
     */
    Class<?> configType();
}
