package com.ublblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ublblog.dao.FriendlyLinkMapper;
import com.ublblog.model.FriendlyLink;
import com.ublblog.model.FriendlyLinkExample;
import com.ublblog.service.FriendlyLinkService;
@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {

	@Autowired
	private FriendlyLinkMapper frLinkMapper;
	
	@Override
	public List<FriendlyLink> getFriendlyLinks() {
		FriendlyLinkExample exam = new FriendlyLinkExample();
		return frLinkMapper.selectByExample(exam);
	}

	@Override
	public boolean addFriendlyLink(FriendlyLink friendlyLink) {
		int result = frLinkMapper.insert(friendlyLink);
		return result>0;
	}

	@Override
	public boolean updateFriendLink(FriendlyLink friendlyLink) {
		int result = frLinkMapper.updateByExample(friendlyLink, getFrLinkExam(friendlyLink));
		return result>0;
	}

	@Override
	public boolean deleteFriendLink(FriendlyLink friendlyLink) {
		int result = frLinkMapper.deleteByPrimaryKey(friendlyLink.getId());
		return result >0;
	}
	
	private FriendlyLinkExample getFrLinkExam(FriendlyLink link)
	{
		FriendlyLinkExample exam = new FriendlyLinkExample();
		if(link.getName() != null)
			exam.or().andNameEqualTo(link.getName());
		if(link.getLink() != null)
			exam.or().andLinkEqualTo(link.getLink());
		return exam;
	}

}
