package com.whatshappen.topgrid.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.util.Log;

import com.whatshappen.topgrid.dao.ChannelDao;
import com.whatshappen.topgrid.db.SQLHelper;

public class ChannelManage {
	public static ChannelManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<ChannelItem> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<ChannelItem> defaultOtherChannels;
	private ChannelDao channelDao;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;
	static {
		defaultUserChannels = new ArrayList<ChannelItem>();
		defaultOtherChannels = new ArrayList<ChannelItem>();
		defaultUserChannels.add(new ChannelItem(1, "头条", 1, 1,0));
		defaultUserChannels.add(new ChannelItem(2, "视频", 2, 1,0));
		defaultUserChannels.add(new ChannelItem(3, "娱乐", 3, 1,0));
		defaultUserChannels.add(new ChannelItem(4, "体育", 4, 1,0));
		defaultUserChannels.add(new ChannelItem(5, "北京", 5, 1,0));
		defaultUserChannels.add(new ChannelItem(6, "新时代", 6, 1,0));
		defaultUserChannels.add(new ChannelItem(7, "网易号", 7, 1,0));
		defaultOtherChannels.add(new ChannelItem(8, "段子", 1, 0,0));
		defaultOtherChannels.add(new ChannelItem(9, "冰雪运动", 2, 0,0));
		defaultOtherChannels.add(new ChannelItem(10, "科技", 3, 0,0));
		defaultOtherChannels.add(new ChannelItem(11, "汽车", 4, 0,0));
		defaultOtherChannels.add(new ChannelItem(12, "轻松一刻", 5, 0,0));
		defaultOtherChannels.add(new ChannelItem(13, "时尚", 6, 0,0));
		defaultOtherChannels.add(new ChannelItem(14, "直播", 7, 0,0));
		defaultOtherChannels.add(new ChannelItem(15, "图片", 8, 0,0));
		defaultOtherChannels.add(new ChannelItem(16, "跟帖", 9, 0,0));
		defaultOtherChannels.add(new ChannelItem(17, "NBA", 10, 0,0));
		defaultOtherChannels.add(new ChannelItem(18, "态度公开课", 11, 0,0));
	}

	private ChannelManage(SQLHelper paramDBHelper) throws SQLException {
		if (channelDao == null)
			channelDao = new ChannelDao(paramDBHelper.getContext());
		return;
	}

	/**
	 * 初始化频道管理类
	 * @param dbHelper
	 * @throws SQLException
	 */
	public static ChannelManage getManage(SQLHelper dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new ChannelManage(dbHelper);
		return channelManage;
	}

	/**
	 * 清除所有的频道
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<ChannelItem> getUserChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?",new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty()) {
			userExist = true;
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			List<ChannelItem> list = new ArrayList<ChannelItem>();
			for (int i = 0; i < count; i++) {
				ChannelItem navigate = new ChannelItem();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				navigate.setNewItem(Integer.valueOf(maplist.get(i).get(SQLHelper.ISNEWITEM)));
				list.add(navigate);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}
	
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<ChannelItem> getOtherChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?" ,new String[] { "0" });
		List<ChannelItem> list = new ArrayList<ChannelItem>();
		if (cacheList != null && !((List) cacheList).isEmpty()){
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++) {
				ChannelItem navigate= new ChannelItem();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				navigate.setNewItem(Integer.valueOf(maplist.get(i).get(SQLHelper.ISNEWITEM)));
				list.add(navigate);
			}
			return list;
		}
		if(userExist){
			return list;
		}
		cacheList = defaultOtherChannels;
		return (List<ChannelItem>) cacheList;
	}
	
	/**
	 * 保存用户频道到数据库
	 * @param userList
	 */
	public void saveUserChannel(List<ChannelItem> userList) {
		for (int i = 0; i < userList.size(); i++) {
			ChannelItem channelItem = (ChannelItem) userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * 保存其他频道到数据库
	 * @param otherList
	 */
	public void saveOtherChannel(List<ChannelItem> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			ChannelItem channelItem = (ChannelItem) otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * 初始化数据库内的频道数据
	 */
	private void initDefaultChannel(){
		Log.d("deleteAll", "deleteAll");
		deleteAllChannel();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
