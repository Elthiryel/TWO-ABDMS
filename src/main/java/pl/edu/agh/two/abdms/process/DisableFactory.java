package pl.edu.agh.two.abdms.process;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to mark NodeFactory implementations that are not supposed to
 * be automatically discovered, istantiated and used.
 *
 * @see NodeFactory (part about automatic discovery in project's package)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableFactory { }
