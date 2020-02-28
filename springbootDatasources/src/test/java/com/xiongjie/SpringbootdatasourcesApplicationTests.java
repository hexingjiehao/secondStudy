package com.xiongjie;

import com.xiongjie.entity.Book;
import com.xiongjie.mapper.test1.BookOneMapper;
import com.xiongjie.mapper.test2.BookTwoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdatasourcesApplicationTests {

	@Autowired
	private BookOneMapper bookOneMapper;
	@Autowired
	private BookTwoMapper bookTwoMapper;

	@Test
	public void contextLoads() {

		Book bookOne=bookOneMapper.getBookByName("c++");
		System.out.println(bookOne);
		Book bookTwo=bookTwoMapper.getBookByName("java");
		System.out.println(bookTwo);

	}

}
