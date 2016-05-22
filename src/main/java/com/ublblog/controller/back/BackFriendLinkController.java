package com.ublblog.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ublblog.controller.BaseController;
import com.ublblog.model.FriendlyLink;

@Controller
@RequestMapping("admin/frlink")
public class BackFriendLinkController extends BaseController{
	/**
	 * 更新标签
	 * @param link
	 * @return String
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateFlink(FriendlyLink link) {
		try {
			if (friendlyLinkService.updateFriendLink(link)) return SUCCESS;
		} catch (Exception e) {
			logger.error("FriendLinkController.updateFlink()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 添加链接
	 * @param link
	 * @return String
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addFlink(FriendlyLink link) {
		link.setId(null);
		try {
			if (friendlyLinkService.addFriendlyLink(link)) return SUCCESS;
		} catch (Exception e) {
			logger.error("FriendLinkController.addFlink()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 删除链接
	 * @param link
	 * @return String
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteFlink(FriendlyLink link) {
		try {
			if (friendlyLinkService.deleteFriendLink(link)) return SUCCESS;
		} catch (Exception e) {
			logger.error("FriendLinkController.deleteFlink()",e.getMessage());
		}
		return FAIL;
	}
}
