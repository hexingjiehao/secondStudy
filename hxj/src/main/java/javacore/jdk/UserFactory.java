package javacore.jdk;

import javacore.clone.User;

/**
 * Created by xiongjie on 2018/10/19.
 */
public interface UserFactory<P extends User> {

    P create();

}
