package other.jmx;

import javax.management.*;

public class SimpleDynamicMBean implements DynamicMBean {
    private String name = "iamzhongyong";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName(){
        System.out.println(name);
    }

    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if(attribute==null){
            throw new AttributeNotFoundException();
        }
        if("name".equalsIgnoreCase(attribute)){
            return getName();
        }
        throw new AttributeNotFoundException();
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        String name = attribute.getName();
        Object value = attribute.getValue();
        if("name".equalsIgnoreCase(name)){
            this.setName(String.valueOf(value));
            return;
        }
        throw new AttributeNotFoundException();
    }

    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if("printName".equals(actionName)){
            printName();
        }
        return null;
    }

    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[] {
                new MBeanAttributeInfo("name", "String", "缓存名称", true,  true, false)};
        MBeanOperationInfo opers[] = {
                new MBeanOperationInfo("printName","print",null,"void",MBeanOperationInfo.ACTION)};

        MBeanInfo info = new MBeanInfo(this.getClass().getName(),
                "SimpleDynamicMBean",
                dAttributes,
                null,
                opers,
                null);
        return info;
    }
}
