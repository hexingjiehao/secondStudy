package com.xiongjie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 类注解表示注入模拟对象
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo {

    //模拟的注解
    @Spy List hello=new ArrayList();
    @Mock List world=new ArrayList();

    public MockitoDemo() {
        //初始化模拟对象
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockAnnotation(){
        hello.add(1);
        verify(hello).add(1);
    }

    public static void main(String[] args){

        mockCreate();
        mockStub();
        mockArgumentMatchers();
        mockInvocateNum();
        mockStubMethodException();
        mockInOrder();
        mockNeverOrZero();
        mockSpy();
        mockReset();
        mockTimeout();
        mockCustomError();
        mockLambdaMatcher();
        mockDoAnswer();
        mockJUnit();
        mockVerifyNoMore();

    }

    private static void mockVerifyNoMore() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list,times(2)).add(anyInt());
        //检查模拟对象是否还有方法调用没有进行检验
        verifyNoMoreInteractions(list);
    }

    private static void mockJUnit() {
        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        System.out.println(result);
        assertEquals("hello world world",result);
    }

    private static void mockDoAnswer() {
        List mock = mock(List.class);
        doAnswer( invocation ->  ((String)invocation.getArgument(1)).length() ).when(mock).add( anyString() );
    }

    private static void mockLambdaMatcher() {
        //在模拟方法调用时，参数进行了部分过滤
        List list = mock(List.class);
        list.add("h");
        verify(list).add(argThat(string -> ((String)string).length() < 5));


        verify(list).add(argThat(new ArgumentMatcher(){
            public boolean matches(Object arg) {
                return ((String)arg).length() < 5;
            }
        }));


        when(list.add(argThat( collect -> ((List)collect).size()<3))).thenReturn(true);
    }

    private static void mockCustomError() {
        //try..catch..不能捕获自定义的异常信息,因为抛出来的是错误，不是异常
        List mockedList = mock(List.class);
        try {
            verify(mockedList, times(2).description("模拟方法应该被调用两次")).add("hello");
        }catch (Error e){
            System.out.println("模拟方法应该被调用两次");
        }
    }

    private static void mockTimeout() {
        MyMock mock=mock(MyMock.class);
        //这里并不会调用真实的方法
        mock.someMethod();

        verify(mock, timeout(100)).someMethod();
        verify(mock, timeout(100).times(1)).someMethod();
    }

    private static void mockReset() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);
        reset(mock);
//        verify(mock).add(1);
    }

    private static void mockSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));
        System.out.println(spy.size());

        verify(spy).add("one");
        verify(spy).add("two");
        System.out.println(list+"："+spy);

        //陷阱。真正方法可能会报错，但模拟方法不会。特殊的打桩方式。doReturn()
        when(spy.get(0)).thenReturn("foo");
        doReturn("foo").when(spy).get(0);
    }


    /**
     * 不能滥用！！！
     */
    private static void mockRedundantInvocation() {
        List mockedList = mock(List.class);
        mockedList.add("one");
//        mockedList.add("two");
        verifyNoMoreInteractions(mockedList);
    }

    private static void mockNeverOrZero() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        mockOne.add("one");
        verify(mockOne).add("one");
        verify(mockOne, never()).add("two");
        verifyZeroInteractions(mockTwo, mockThree);
    }

    private static void mockInOrder() {
        //验证同一个模拟对象的方法的调用顺序
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        //验证不同模拟对象的方法调用顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        firstMock.add("was called first");
        secondMock.add("was called second");
        inOrder = inOrder(firstMock, secondMock);

        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");
    }

    private static void mockStubMethodException() {
        List mockedList = mock(List.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        try {
            mockedList.clear();
        }catch (Exception e){
            System.out.println( "模拟方法抛异常："+e.toString() );
        }
    }

    private static void mockInvocateNum() {
        List mockedList = mock(List.class);
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //默认是验证某个方法调用1次了。
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");
        verify(mockedList, never()).add("never happened");

        //验证某个方法的调用次数的范围
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    private static void mockArgumentMatchers() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("element");
    }

    private static void mockStub() {
        //模拟方法调用，只管返回值。没有模拟的时候返回null
        LinkedList list = mock(LinkedList.class);
        when(list.get(0)).thenReturn("first");
        when(list.get(0)).thenReturn("second");
        System.out.println(list.get(0));
        System.out.println(list.get(999));

        //验证该步骤有没有进行操作，被打桩的值永远一致，后面的打桩会覆盖前面的
        verify(list).get(0);
    }

    private static void mockCreate() {
        //模拟创建对象
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


}
