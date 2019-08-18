package com.itheima.mapper;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    /**
     * 一对一
     * 根据订单号查询订单信息，并且查询出下单人信息
     * @param number
     * @return
     */
    public Order queryOrderWithUser(@Param("number")String number);


    /**
     * 一对多
     * 查询订单，查询出下单人信息并且查询出订单详情。
     * @param number
     * @return
     */
    public Order queryOrderWithUserDetail(@Param("number")String number);

    /**
     * 查询订单，查询出下单人信息并且查询出订单详情中的商品数据。
     * @param number
     * @return
     */
    public Order queryOrderWithUserDetailItem(@Param("number")String number);

}
