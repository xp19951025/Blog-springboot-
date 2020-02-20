package com.blog.ln.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ln.bean.UserInfo;
import com.blog.ln.dao.UserInfoMapper;
import com.blog.ln.service.UserInfoService;
import com.blog.ln.utils.Const;
import com.blog.ln.utils.PageBean;
import com.blog.ln.utils.PageUtil;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper usermapper;
	
	//查询并分页
	@Override
	public PageBean<UserInfo> getList(UserInfo user, Integer page) {
		
		//总记录数
		int allRow = usermapper.getUserCount(user).intValue();
		//总页数
		int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);
		//当前第几页
		int currentPage = PageUtil.countCurrentPage(page);
		//起始记录数
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);
		
		if(page>=0) {
			user.setStart(start);
			user.setLength(Const.PAGE_SIZE);
		}else {
			user.setStart(-1);
			user.setLength(-1);
		}
		
		List<UserInfo> users = usermapper.getUserList(user);
		
		PageBean<UserInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(users);
		
		return pageBean;
	}
	
	//根据条件查询用户信息
	@Override
	public UserInfo getUser(UserInfo user) {
		// TODO Auto-generated method stub
		if(user != null && user.getUserId() != null) {
			return usermapper.selectByPrimaryKey(user.getUserId());
		}
		return null;
		
	}

	//添加用户
	@Override
	public boolean add(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			if (user != null) {
				user.setUserMark(Const.MARK_YES);
			}
			
			int insertSelective = usermapper.insertSelective(user);
			
			if (insertSelective > 0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}

	//修改用户信息
	@Override
	public boolean update(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			if (user != null) {
				user.setUserMark(Const.MARK_YES);
			}
			int insertSelective = usermapper.updateByPrimaryKeySelective(user);
			if (insertSelective > 0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	//删除用户信息
	@Override
	public boolean delete(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			
			user.setUserMark(Const.MARK_NO);
			
			int insertSelective = usermapper.updateByPrimaryKeySelective(user);
			if (insertSelective > 0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	//用户登陆
	@Override
	public UserInfo isLogin(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			return usermapper.isLogin(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return null;
	}

	/**
	 * 获取用户总数
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return usermapper.getUserCount(null).intValue();
	}


}
