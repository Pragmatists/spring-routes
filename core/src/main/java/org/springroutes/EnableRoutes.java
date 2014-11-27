package org.springroutes;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ScriptHandler.class})
public @interface EnableRoutes {

    String[] value() default {};

}