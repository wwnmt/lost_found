package com.cx.lost_found.dao;

import com.cx.lost_found.entity.AreaDAO;

public interface AreaDAOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    int insert(AreaDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    int insertSelective(AreaDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    AreaDAO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(AreaDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table area
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    int updateByPrimaryKey(AreaDAO record);
}