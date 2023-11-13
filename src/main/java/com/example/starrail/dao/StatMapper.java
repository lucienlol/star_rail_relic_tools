package com.example.starrail.dao;

import com.example.starrail.po.Stat;
import com.example.starrail.po.StatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    long countByExample(StatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int deleteByExample(StatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int deleteByPrimaryKey(Integer statId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int insert(Stat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int insertSelective(Stat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    List<Stat> selectByExample(StatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    Stat selectByPrimaryKey(Integer statId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByExampleSelective(@Param("record") Stat record, @Param("example") StatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByExample(@Param("record") Stat record, @Param("example") StatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByPrimaryKeySelective(Stat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByPrimaryKey(Stat record);
}