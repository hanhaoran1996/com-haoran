package com.haoran.data.elastic;

import com.haoran.common.Const;

import java.lang.annotation.*;

/**
 * @author hr.han
 * @date 2019/6/5 10:01
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DocField {

    boolean index() default true;

    String name() default Const.EMPTY;
}
