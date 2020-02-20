package com.blog.ln.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ln.bean.MessageInfo;
import com.blog.ln.dao.MessageInfoMapper;
import com.blog.ln.service.MessageService;
import com.blog.ln.utils.Const;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageInfoMapper messagemapper;
	
	/**
	 * 查询留言信息
	 */
	@Override
	public List<MessageInfo> getList(MessageInfo info) {
		
		return messagemapper.getMessageList(info);
	}

	/**
	 * 修改留言信息
	 */
	@Override
	public boolean update(MessageInfo info) {
		try {
			int count = messagemapper.updateByPrimaryKeySelective(info);
			if(count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 删除留言
	 */
	@Override
	public boolean deleteme(Integer meid) {
		try {
			int count = messagemapper.deleteByPrimaryKey(meid);
			if(count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 添加留言
	 */
	@Override
	public boolean addmessage(MessageInfo messageinfo) {
		try {
			if(messageinfo != null) {
				messageinfo.setMessageMark(Const.MARK_YES);
				messageinfo.setMessageTime(new Date());
			}
			int count = messagemapper.insertSelective(messageinfo);
			if(count > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 查询留言总数
	 */
	@Override
	public Long messamount(MessageInfo messageinfo) {
		//查询留言总数
		return messagemapper.messamount(messageinfo);
	}

}
