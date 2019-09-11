package cn.itcast.dao;

import java.util.List;

import cn.itcast.pojo.User;

public interface PhoenixHbaseDao {
	  public List<User> query(String querySql);
	  public void update(String querySql);
	  public void batchUpdate (String updateSQL);
}
