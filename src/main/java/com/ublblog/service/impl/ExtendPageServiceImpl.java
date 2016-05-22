package com.ublblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ublblog.dao.ExtendPageMapper;
import com.ublblog.model.ExtendPage;
import com.ublblog.model.ExtendPageExample;
import com.ublblog.service.ExtendPageService;

@Service
public class ExtendPageServiceImpl implements ExtendPageService {

	@Autowired
	private ExtendPageMapper extendPageMapper;
	
	@Override
	public ExtendPage getPage(Integer pageId) {
		ExtendPage page = extendPageMapper.selectByPrimaryKey(pageId);
		return page;
	}

	@Override
	public List<ExtendPage> getAllPages() {
		return extendPageMapper.selectByExample(getExample());
	}

	@Override
	public boolean updatePageInfo(ExtendPage page) {
		if(page.getDisplay() == null)
		{
			page.setDisplay(1);
		}
		int result = extendPageMapper.updateByPrimaryKeyWithBLOBs(page);
		return result>0;
	}

	@Override
	public boolean addPage(ExtendPage page) {
		int result = extendPageMapper.insert(page);
		return result>0;
	}

	@Override
	public boolean deletePage(Integer pageId) {
		ExtendPage page = getPage(pageId);
		
		int result = 0;
		if(page.getDisplay() == (Integer)0 || page.getDisplay() == null )
		{
			page.setDisplay(1);
			result = extendPageMapper.updateByPrimaryKeyWithBLOBs(page);
		}
		else
		{
			result = extendPageMapper.deleteByPrimaryKey(pageId);
		}
		return result>0;
	}
	
	private ExtendPageExample getExample()
	{
		ExtendPageExample exam = new ExtendPageExample();
		return exam;
	}

}
