package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {
     /**
      * 若sql语句查询结果为多条时，一定不能以实体类类型作为方法的返回值
      * 否则会抛出异常
      * 若sql语句查询结果为一条时，此时可以使用实体类类型或list集合类型作为方法的返回值
      */

     //根据id查询用户信息
     User getUserById(@Param("id") Integer id);

     //查询所有的用户信息
     List<User> getAllUser();

     //查询用户的总数量
     Integer getCount();

     //根据id查询用户信息为map集合
     Map<String,Object> getUserByIdToMap(@Param("id") Integer id);

     /**
      * 查询所有的用户信息为map集合
      * 若查询的数量有多条时，并且要将每条数据转换为map集合
      * 此时有两种解决方案
      * 1.将mapper接口的返回值设置为泛型时map的list集合
      * 2.可以将每条数据转换的map集合放在一个大的map集合中，但是必须要通关@MapKey注解
      *   将查询的某个字段的值作为大的map的键
      *
      * @return
      */
//     List<Map<String,Object>> getAllUserToMap();
     @MapKey("id")
     Map<String,Object> getAllUserToMap();
}
