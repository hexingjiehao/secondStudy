package com.xiongjie;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsNotANumber extends TypeSafeMatcher {

    @Override
    public boolean matchesSafely(Object number) {
        return ((Double)number).isNaN();
    }

    //自定义描述
    public void describeTo(Description description) {
        description.appendText("not a number");
    }

    @Factory
    public static Matcher notANumber() {
        return new IsNotANumber();
    }

}