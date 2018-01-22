package com.datasource;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.bean.Device;
import com.bean.User;
import com.bean.UserHostRelation;
import com.utility.MD5Util;
import com.utility.SqlUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbDAO {
	
	
	private static final Logger logger = LogManager.getLogger(DbDAO.class);

	public User queryUserByUserName(String userName, String passWord) {

		User user = new User();
		user.setUserName(userName);
		user.setPassword(MD5Util.encryptionMD5(passWord));

		return getUserByConditions(user);
	}

	public User getUserByAmazonId(String amazonId) {
		User user = new User();
		user.setAmazonId(amazonId);
		return getUserByConditions(user);
	}
	
	
	public User getUserByEmail(String amazonId) {
		User user = new User();
		user.setEmail(amazonId);
		return getUserByConditions(user);
	}

	public User getUserByConditions(User user) {

		DruidPooledConnection connection = null;
		User tempuser = new User();
		try {
			connection = DbPoolConnection.getInstance().getConnection();
			SqlUtil<User> sqlUtil = new SqlUtil<User>();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlUtil.getSqlByConditions(user, "user"));

			ResultSet resultList = preparedStatement.executeQuery();

			if (resultList.next()) {

				String userId = resultList.getString("userId");
				String userName = resultList.getString("userName");
				String amazonId = resultList.getString("amazonId");
				String password = resultList.getString("password");

				tempuser.setUserId(userId);
				tempuser.setUserName(userName);
				tempuser.setAmazonId(amazonId);
				tempuser.setPassword(password);
				return tempuser;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public void updateHostByConditions(Device user) {

		DruidPooledConnection connection = null;

		try {
			connection = DbPoolConnection.getInstance().getConnection();
			String sql=" update user_house_equipment_relation set name = ? , devid = ? where equipmentMac=? and equipmentEp=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getDevid());
			preparedStatement.setString(3, user.getDeviceMac());
			preparedStatement.setString(4, user.getEquipmentEp());
			preparedStatement.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<UserHostRelation> getHostByConditions(UserHostRelation user) {

		DruidPooledConnection connection = null;

		try {
			connection = DbPoolConnection.getInstance().getConnection();
			SqlUtil<UserHostRelation> sqlUtil = new SqlUtil<UserHostRelation>();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlUtil.getSqlByConditions(user, "user_host_relation"));

			ResultSet resultList = preparedStatement.executeQuery();

			List<UserHostRelation> lists = new ArrayList<UserHostRelation>();

			while (resultList.next()) {
				UserHostRelation host = new UserHostRelation();

				String userId = resultList.getString("userId");
				String mac = resultList.getString("mac");
				String hostName = resultList.getString("hostName");

				host.setUserId(userId);
				host.setHostName(hostName);
				host.setHostMac(mac);
				lists.add(host);
			}

			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<UserHostRelation> queryHostRelateThisUser(String userId) {

		UserHostRelation host = new UserHostRelation();
		host.setUserId(userId);

		return getHostByConditions(host);
	}

	public List<UserHostRelation> getHostByAmazonId1(String amazonId) {

		UserHostRelation host = new UserHostRelation();
		host.setAmazonId(amazonId);

		return getHostByConditions(host);
	}

	public List<UserHostRelation> queryUserByHostmac(String hostMac) {

		UserHostRelation host = new UserHostRelation();
		host.setHostMac(hostMac);

		return getHostByConditions(host);
	}

	public List<Device> queryDeviceDatail(String userId) {

		DruidPooledConnection connection = null;
		try {
			connection = DbPoolConnection.getInstance().getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement("select  user_house_equipment_relation.userId,house.houseName,floor.floorName,user_house_equipment_relation.hostMac,user_house_equipment_relation.equipmentEp,user_house_equipment_relation.equipmentMac,user_house_equipment_relation.`name`,user_house_equipment_relation.devid from user_house_equipment_relation  JOIN house  on house.houseId = user_house_equipment_relation.houseId JOIN floor on house.floorId=floor.floorId  WHERE user_house_equipment_relation.userId=?");

			preparedStatement.setString(1, userId);

			ResultSet resultList = preparedStatement.executeQuery();
			List<Device> deviceList = new ArrayList<Device>();

			while (resultList.next()) {
				String usertempId = resultList.getString(1);
				String houseName = resultList.getString(2);
				String floorName = resultList.getString(3);
				String hostMac = resultList.getString(4);
				String equipmentEp = resultList.getString(5);
				String equipmentMac = resultList.getString(6);
				
				String name = resultList.getString(7);
				String devid = resultList.getString(8);

				Device device = new Device();
				device.setUserId(usertempId);
				device.setRoomName(houseName);
				device.setFloorName(floorName);
				device.setDeviceMac(equipmentMac);
				device.setHostMac(hostMac);
				device.setEquipmentEp(equipmentEp);
				device.setName(name);
				device.setDevid(devid);
				deviceList.add(device);
			}

			return deviceList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<Device>();
	}

	public void updateUserByUserName(String userName, String amazonId) {
		DruidPooledConnection connection = null;
		try {
			connection = DbPoolConnection.getInstance().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("update user set amazonId=? where userName =?");
			preparedStatement.setString(1, amazonId);
			preparedStatement.setString(2, userName);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Test
	public void test() {

		User user = new User();
		user.setEmail("ktu@smartplusinc.com");
		User tempUser = getUserByConditions(user);
		System.out.println(tempUser.getAmazonId());

	}

}
