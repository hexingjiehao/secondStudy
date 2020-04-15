package com.xiongjie.mapper.test2;

import com.xiongjie.entity.Book;
import org.springframework.stereotype.Component;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public interface BookTwoMapper {

    Book getBookByName(String name);

}
