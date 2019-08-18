package com.itheima.dao;

import com.itheima.domain.ContactInfo;
import com.itheima.utils.JdbcTempUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ContactInfoDao implements ContactInfDaoImp {

    //注意数据    库版本号与jar包版本的对应，两次的包拼凑起来用~~~~~
    //这一层是个数据库交互的，Jdbct会用到很多，放到成员变量中
    private JdbcTemplate jdbcTemplate = JdbcTempUtils.getJdbcT();

    @Override
    //查询(操作的根源)
    public List<ContactInfo> queryAll() {
        String sql = "select * from contact_info where del=0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class));

    }

    @Override
    //删除行(操作的根源)
    public int delById(String id) {
        String sql = "update contact_info set del= 1 where id= ?";//注意语句的写法！！！
        return jdbcTemplate.update(sql, id);
    }

    @Override
    //添加数据(操作的根源)
    public int addContact(ContactInfo contactInfo) {//直接访问此页面会报错500，因为你都没添加东西！！！（一个小时，还有add.jsp完成后，表单你不跳转？？？？？）
        String sql = "INSERT INTO contact_info(name, gender, birthday, birthplace,mobile, email)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                contactInfo.getName(),
                contactInfo.getGender(),
                contactInfo.getBirthday(),
                contactInfo.getBirthplace(),
                contactInfo.getMobile(),
                contactInfo.getEmail()
        );
    }

    @Override
//    查询记录数(操作的根源)
    public int queryCount() {
        String sql = "SELECT COUNT(*) FROM contact_info WHERE del=0";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    @Override
    //    分页式查询(操作的根源)//与全部查询的函数的区别注意区分 已经踩过
    public List<ContactInfo> queryAllf(int offset, int size) {
        String sql = "SELECT * FROM contact_info WHERE del=0 limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class), offset, size);
    }


    //获取要修改的哪一个对象？？？？？得到的是一个集合，长度为1
    public ContactInfo getContact(String contactId) {
        String sql = "select * from  contact_info where del =0 and id =? ";
        List<ContactInfo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class), contactId);
        if (query.size() == 1) {
            return query.get(0);
        } else {
            //若是返回的不是一个长度唯一的集合，说明出错了。返回一个null、
            return null;
        }

    }

    @Override
    public int updateContact(ContactInfo contact) {
        String sql = "UPDATE contact_info SET  name=?, gender=?, birthday=?, birthplace=?, mobile=?, email=? WHERE id=?";
        return jdbcTemplate.update(sql,
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail(),
                contact.getId());
    }
}


