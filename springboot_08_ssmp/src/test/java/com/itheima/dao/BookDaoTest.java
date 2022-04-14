package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Book;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testGetById() {
        Book book = bookDao.selectById(2);
        System.out.println(book);
    }

    @Test
    public void testSave() {
        Book book = new Book();
        book.setType("测试1");
        book.setName("lmx");
        book.setDescription("lmxyyds");
        bookDao.insert(book);
    }

    @Test
    void testPage() {
        IPage<Book> page = new Page<>(1, 5);
        bookDao.selectPage(page, null);
    }

    @Test
    void testByCondition(){
        String name = "spring";
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(name),Book::getName,name);
        bookDao.selectList(wrapper);
    }
}
