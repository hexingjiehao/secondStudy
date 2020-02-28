package com.xiongjie;

import java.util.Comparator;

public class PersonComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        Person p= (Person) o1;
        Person q= (Person) o2;
        if( p.getName()==q.getName() ){
            return 0;
        }
        return 1;
    }
}
