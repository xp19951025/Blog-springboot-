package com.blog.ln.service;

import java.util.List;

import com.blog.ln.bean.MessageInfo;

public interface MessageService {

	/**
	 * 查询留言信息
	 * @param info
	 * @return
	 */
	public List<MessageInfo> getList(MessageInfo info);
	
	/**
	 * 修改留言信息
	 * @param info
	 * @return
	 */
	public boolean update(MessageInfo info);
	
	/**
	 * 删除留言信息
	 * @param info
	 * @return
	 */
	public boolean deleteme(Integer info);
	
	/**
	 * 添加留言
	 * @param messageinfo
	 * @return
	 */
	public boolean addmessage(MessageInfo messageinfo);
	
	/**
	  * 查询留言总数
	 * @return
	 */
	public Long messamount(MessageInfo messageinfo);
	
}
