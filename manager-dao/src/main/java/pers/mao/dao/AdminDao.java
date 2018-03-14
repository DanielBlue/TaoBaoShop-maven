package pers.mao.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.mao.pojo.Admin;
import pers.mao.pojo.AdminExample;

public interface AdminDao {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);
}