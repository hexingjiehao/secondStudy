package com.xiongjie;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

//1.继承抽象断言类
public class PersonAssert extends AbstractAssert<PersonAssert,Person> {

    //2.写构造方法，之类断言类型
    public PersonAssert(Person person) {
        super(person,PersonAssert.class);
    }

    //3.静态构造方法，流程链式结构
    public static PersonAssert assertThat(Person person){
        return new PersonAssert(person);
    }

    //4.具体的自定义断言
    public PersonAssert hasName(String name){

        isNotNull();

        if (!Objects.equals(actual.getName(), name)) {
            failWithMessage("Expected person's name to be <%s> but was <%s>", name, actual.getName());
        }
        return this;
    }


}
