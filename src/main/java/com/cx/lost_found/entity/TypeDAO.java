package com.cx.lost_found.entity;

public class TypeDAO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column type.id
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column type.typename
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    private String typename;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column type.id
     *
     * @return the value of type.id
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column type.id
     *
     * @param id the value for type.id
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column type.typename
     *
     * @return the value of type.typename
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    public String getTypename() {
        return typename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column type.typename
     *
     * @param typename the value for type.typename
     *
     * @mbg.generated Wed May 01 23:58:22 GMT+08:00 2019
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }
}