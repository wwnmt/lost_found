package com.cx.lost_found.entity;

public class UserDAO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.telephone
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.realname
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    private String realname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.isadmin
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    private Boolean isadmin;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.telephone
     *
     * @return the value of user.telephone
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.telephone
     *
     * @param telephone the value for user.telephone
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.realname
     *
     * @return the value of user.realname
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public String getRealname() {
        return realname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.realname
     *
     * @param realname the value for user.realname
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.isadmin
     *
     * @return the value of user.isadmin
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public Boolean getIsadmin() {
        return isadmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.isadmin
     *
     * @param isadmin the value for user.isadmin
     *
     * @mbg.generated Wed May 01 18:07:20 GMT+08:00 2019
     */
    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }
}