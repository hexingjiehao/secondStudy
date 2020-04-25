package core.clone;

import core.jdk.Hint;
import core.jdk.Hints;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by xiongjie on 2018/10/17.
 */
@Hints({@Hint("hint1"), @Hint("hint2")})
public class User implements Cloneable,
        Serializable{

    @Resource
    private String username;
    private String password;

    public User() {
        username="admin";
        password="321";
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * clone的方法，如果对象只有基本数据类型，可以这样写.
     * 会生成两个相同内容的不同对象
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if (username.equals(user.username) && password.equals(user.password)) {
            return true;
        }
        return false;
    }

}
