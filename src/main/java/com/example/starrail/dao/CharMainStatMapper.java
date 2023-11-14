package com.example.starrail.dao;

import com.example.starrail.po.CharMainStat;
import com.example.starrail.po.CharMainStatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CharMainStatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    long countByExample(CharMainStatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int deleteByExample(CharMainStatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer charMainStatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int insert(CharMainStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int insertSelective(CharMainStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    List<CharMainStat> selectByExample(CharMainStatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    CharMainStat selectByPrimaryKey(Integer charMainStatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CharMainStat record, @Param("example") CharMainStatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CharMainStat record, @Param("example") CharMainStatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CharMainStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_main_stat
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CharMainStat record);
}