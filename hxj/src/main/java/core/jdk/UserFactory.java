package core.jdk;


import core.clone.User;

/**
 * Created by xiongjie on 2018/10/19.
 */
public interface UserFactory<P extends User> {

    P create();

}
