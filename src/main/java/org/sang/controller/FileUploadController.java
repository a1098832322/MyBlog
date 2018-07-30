package org.sang.controller;



import org.sang.utils.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Auther:郑龙
 * @Date:2018-07-26 09:26
 * @Description:
 */
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@Controller
@RequestMapping("/image")
public class FileUploadController {

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
