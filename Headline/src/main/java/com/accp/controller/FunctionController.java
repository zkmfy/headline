
package com.accp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.domain.Consumer;
import com.accp.service.ConsumerService;
import com.accp.utils.MyUtil;

@Controller
@ResponseBody
@RequestMapping("/function/")
public class FunctionController {

	@Autowired
	ConsumerService consumerService;
	
	@RequestMapping("np")
	public void np(int cid,int type,HttpSession session) {
		Consumer consumer=new Consumer();
		consumer.setId(1);
		switch (type) {
		case 1:
			consumerService.cnp(cid, 1);
			break;
		case 0:
			consumerService.cp(cid, 1);			
			break;
		}
	}
	
	@RequestMapping("upload")
	public void upload(MultipartFile file,HttpSession session) {
		String path=session.getServletContext().getRealPath("/static/img/upload/");
		@SuppressWarnings("unchecked")
		List<String> list=(List<String>)session.getAttribute("uploadImgs");
		List<String> imgs = list == null ? new ArrayList<String>() : list;
		String name=MyUtil.getUUID32()+"."+file.getContentType().split("/")[0];
		try {
			file.transferTo(new File(path+name));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgs.add(name);
		session.setAttribute("uploadImgs", imgs);
	}
}
