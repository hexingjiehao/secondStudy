package com.xiongjie;

import static com.xiongjie.IsNotANumber.notANumber;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import junit.framework.TestCase;

/**
 * 继承了测试类的Java类，可以不用在被测试方法上写@Test，也可以进行测试运行
 */
public class BiscuitTest extends TestCase {

    public void testEquals() {
        String theBiscuit = new String("Ginger");
        String myBiscuit = new String("Ginger");
        assertThat(theBiscuit, equalTo(myBiscuit));

        //可以设置断言的标示符，也就是名字
        assertThat("chocolate chips", theBiscuit, equalTo(myBiscuit));
        assertThat("hazelnuts", theBiscuit, equalTo(myBiscuit));

        assertThat(theBiscuit, equalTo(myBiscuit));
        assertThat(theBiscuit, is(equalTo(myBiscuit)));
        assertThat(theBiscuit, is(myBiscuit));

        //使用自定义适配器
        assertThat(Math.sqrt(-1), is(notANumber()));
    }
} 