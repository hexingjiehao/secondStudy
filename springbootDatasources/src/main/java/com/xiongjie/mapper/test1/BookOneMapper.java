package com.xiongjie.mapper.test1;

import com.xiongjie.entity.Book;
import org.springframework.stereotype.Component;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public interface BookOneMapper {

    Book getBookByName(String name);

}
