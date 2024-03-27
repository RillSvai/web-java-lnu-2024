package org.lnu.smartphoneservice.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface TrackExecution {
    boolean isEnabled() default true;

    boolean isExecutionTimeEnabled() default true;
}
