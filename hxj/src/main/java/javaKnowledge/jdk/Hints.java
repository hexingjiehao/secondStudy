package javaKnowledge.jdk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by xiongjie on 2018/10/19.
 */
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface Hints {
    Hint[] value();
}
