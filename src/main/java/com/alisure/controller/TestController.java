package com.alisure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ALISURE on 2017/3/17.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("hello world");
        out.close();
    }
}
