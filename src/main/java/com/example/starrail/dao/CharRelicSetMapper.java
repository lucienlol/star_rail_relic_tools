package com.example.starrail.dao;

import com.example.starrail.po.CharRelicSet;
import com.example.starrail.po.CharRelicSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CharRelicSetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    long countByExample(CharRelicSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int deleteByExample(CharRelicSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int deleteByPrimaryKey(Integer charRelicSetId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int insert(CharRelicSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int insertSelective(CharRelicSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    List<CharRelicSet> selectByExample(CharRelicSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    CharRelicSet selectByPrimaryKey(Integer charRelicSetId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByExampleSelective(@Param("record") CharRelicSet record, @Param("example") CharRelicSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByExample(@Param("record") CharRelicSet record, @Param("example") CharRelicSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByPrimaryKeySelective(CharRelicSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table char_relic_set
     *
     * @mbg.generated Sat Nov 11 15:15:58 CST 2023
     */
    int updateByPrimaryKey(CharRelicSet record);
}