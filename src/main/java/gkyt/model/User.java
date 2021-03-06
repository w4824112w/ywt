package gkyt.model;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.username
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.salt
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.hashed_password
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private String hashedPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.role
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private Integer role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.jail_id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private Integer jailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.created_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.updated_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.id
     *
     * @return the value of users.id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.id
     *
     * @param id the value for users.id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.username
     *
     * @return the value of users.username
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.username
     *
     * @param username the value for users.username
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.salt
     *
     * @return the value of users.salt
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.salt
     *
     * @param salt the value for users.salt
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.hashed_password
     *
     * @return the value of users.hashed_password
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.hashed_password
     *
     * @param hashedPassword the value for users.hashed_password
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword == null ? null : hashedPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.role
     *
     * @return the value of users.role
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.role
     *
     * @param role the value for users.role
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.jail_id
     *
     * @return the value of users.jail_id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public Integer getJailId() {
        return jailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.jail_id
     *
     * @param jailId the value for users.jail_id
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setJailId(Integer jailId) {
        this.jailId = jailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.created_at
     *
     * @return the value of users.created_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.created_at
     *
     * @param createdAt the value for users.created_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.updated_at
     *
     * @return the value of users.updated_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.updated_at
     *
     * @param updatedAt the value for users.updated_at
     *
     * @mbggenerated Fri Jun 09 10:21:25 CST 2017
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}