package other.jmx;

public class SimpleMBeanImpl implements SimpleMBean {

    private String name;

    public void sayHello() {
        System.out.println("hello,"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }
}
