package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.controller.utils.R;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R getAll() {
        return new R(true, bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) {
        return new R(bookService.save(book));
    }

    @PutMapping
    public R update(@RequestBody Book book) {
        return new R(bookService.updateById(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.removeById(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true, bookService.getById(id));
    }

    /*   @GetMapping("{currentPage}/{pageSize}")
       public R page(@PathVariable int currentPage, @PathVariable int pageSize) {
           IPage<Book> iPage = new Page<>(currentPage, pageSize);
           IPage<Book> page = bookService.page(iPage);
           //如果当前页码值大于总页码值，那么重新执行查询操作，室友最大页码值作为当前页码值
           if (currentPage > page.getPages()){
               iPage = new Page<>(page.getPages(),pageSize);
               page = bookService.page(iPage);
           }
           return new R(true, page);
       }*/
    @GetMapping("{currentPage}/{pageSize}")
    public R getAll(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {
        System.out.println("参数=====>" + book);
        IPage<Book> pageBook = bookService.getPage(currentPage, pageSize, book);
        if (currentPage > pageBook.getPages()) {
            pageBook = bookService.getPage((int) pageBook.getPages(), pageSize, book);
        }
        return new R(null != pageBook, pageBook);
    }
}
