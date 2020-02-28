package javaKnowledge.jdk;

import java.lang.annotation.Repeatable;

/**
 * Created by xiongjie on 2018/10/19.
 */
//@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
