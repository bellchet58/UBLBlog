package com.ublblog.controller.back;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ublblog.controller.BaseController;

@Controller
@RequestMapping("/upload")
public class FileUploadController extends BaseController{

	@RequestMapping(value="/", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/avatar", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
            @RequestParam("avatar") MultipartFile file,HttpServletRequest request){
		String name = file.getOriginalFilename();
		String path = request.getRealPath("/")+name;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                logger.debug("servlet path is {},the name is {},the originalFileName is {}",request.getRealPath("/")+name,name,file.getOriginalFilename());
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                return SUCCESS;
            } catch (Exception e) {
                return ERROR;
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
		
}
