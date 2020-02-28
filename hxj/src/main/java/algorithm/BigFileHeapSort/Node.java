package algorithm.BigFileHeapSort;

import java.util.ArrayList;
import java.util.List;

public class Node {

    String value;
    int times;

    public Node(String value) {
        this.value = value;
        this.times=1;
    }

    public Node(String value, int times) {
        this.value = value;
        this.times = times;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void addOne(){
        this.times++;
    }

    public List<String> convert(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<times;i++){
            list.add(value);
        }
        return list;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", times=" + times +
                '}';
    }
}
