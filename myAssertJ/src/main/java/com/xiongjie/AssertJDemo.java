package com.xiongjie;

import org.assertj.core.api.Condition;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.awt.font.NumericShaper;
import java.awt.font.NumericShaper.Range;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class AssertJDemo {

    @Test
    public void test(){
        assertThat(1).isEqualTo(1);

        //描述断言失败后的信息
        assertThat(1).as("check %s's", "input value").isEqualTo(1);
        filterOnCondition();
        iterableArray();
        fileAssertion();
        customComparison();
        AssertComparingFieldByField();
        assertCondition();
        customAssert();

    }

    private void customAssert() {
        Person p=new Person("张三","read","上海","QQ");
        PersonAssert.assertThat(p).hasName("张三");
    }

    private void assertCondition() {
        Condition<String> jedi = new Condition<String>("jedi") {
            @Override
            public boolean matches(String value) {
                return value=="hello";
            }
        };
        assertThat("hello").is(jedi);
    }

    private void AssertComparingFieldByField() {
        Person p=new Person("张三","read","上海","QQ");
        Person q=new Person("张三","read","上海","QQ");
        assertThat(p).isEqualToComparingFieldByField(q);
    }

    private void customComparison() {
        Person p=new Person("张三","read","上海","QQ");
        Person q=new Person("张三","read","上海","QQ");
        PersonComparator personComparator=new PersonComparator();
        assertThat(p).usingComparator(personComparator).isEqualTo(q);
    }

    private void fileAssertion() {
        File file=new File("src/main/java/com/xiongjie/AssertJ.txt");
        assertThat(file).exists().isFile().isRelative();
        assertThat(contentOf(file)).startsWith("The Truth").contains("Is Out").endsWith("There");
    }

    @Test
    public void softAssertCollect() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(1).as("断言1").isEqualTo(1);
        softly.assertThat(2).as("断言2").isEqualTo(2);
        softly.assertThat(3).as("断言3").isEqualTo(1);
        softly.assertThat(4).as("断言4").isEqualTo(4);
        softly.assertThat(5).as("断言5").isEqualTo(5);
        softly.assertThat(6).as("断言6").isEqualTo(1);
        softly.assertAll();
    }

    private void iterableArray() {
        List<Person> list=new ArrayList<>();
        list.add(new Person("张三","read","上海","QQ"));
        list.add(new Person("李四","write","北京","163"));
        list.add(new Person("王五","book","成都","outlook"));
        list.add(new Person("赵六","game","山西","126"));
        assertThat(list).extracting("name", "address", "email")
                .contains(tuple("张三","上海","QQ"),
                        tuple("王五","成都","outlook")
                        );
    }

    private void filterOnCondition() {
        //Condition条件的使用,用过滤后的值来进行比较
        Condition<Integer> mvpStats= new Condition<Integer>() {
            @Override
            public boolean matches(Integer integer) {
                return integer>8;
            }
        };
        List<Integer> players=new ArrayList<>();
        players.add(2);
        players.add(5);
        players.add(10);
        assertThat(players).filteredOn(mvpStats).contains(10);
    }

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();
    @Test
    public void JunitSoftAssertion() {
        softly.assertThat(1).as("断言1").isEqualTo(1);
        softly.assertThat(2).as("断言2").isEqualTo(2);
        softly.assertThat(3).as("断言3").isEqualTo(1);
        softly.assertThat(4).as("断言4").isEqualTo(4);
        softly.assertThat(5).as("断言5").isEqualTo(5);
        softly.assertThat(6).as("断言6").isEqualTo(1);
    }

    @Test
    public void testException() {
        assertThatThrownBy(() -> { throw new Exception("boom!"); }).isInstanceOf(Exception.class)
                .hasMessageContaining("boom");

        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { throw new IOException("boom!"); })
                .withMessage("%s!", "boom")
                .withMessageContaining("boom")
                .withNoCause();

        Throwable thrown = catchThrowable(() -> { throw new Exception("boom!"); });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("boom");
    }


}
