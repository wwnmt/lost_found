package com.cx.lost_found.dao;

import com.cx.lost_found.entity.CommentDAO;

public interface CommentDAOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    int insert(CommentDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    int insertSelective(CommentDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    CommentDAO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(CommentDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Fri May 03 21:26:06 GMT+08:00 2019
     */
    int updateByPrimaryKey(CommentDAO record);
}