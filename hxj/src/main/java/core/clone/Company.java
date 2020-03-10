package core.clone;

import java.io.Serializable;

/**
 * Created by xiongjie on 2018/10/17.
 */
public class Company implements Cloneable,
        Serializable {

    private User user;
    private String address;

    public Company(User user, String address) {
        super();
        this.user = user;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();  浅克隆

        //深克隆的一种方式，每个内部对象调用clone方法
        Company company= (Company) super.clone();
        company.user = (User) company.getUser().clone();
        return company;
    }

    @Override
    public boolean equals(Object obj) {
        Company company = (Company) obj;

        if (user.equals(company.getUser()) && address.equals(company.address)) {
            return true;
        }
        return false;
    }

}
