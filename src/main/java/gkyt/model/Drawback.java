package gkyt.model;

import java.math.BigDecimal;
import java.util.Date;

public class Drawback {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.family_id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private Integer familyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.figure
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private BigDecimal figure;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.created_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.updated_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column drawbacks.status
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.id
     *
     * @return the value of drawbacks.id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.id
     *
     * @param id the value for drawbacks.id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.family_id
     *
     * @return the value of drawbacks.family_id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.family_id
     *
     * @param familyId the value for drawbacks.family_id
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.figure
     *
     * @return the value of drawbacks.figure
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public BigDecimal getFigure() {
        return figure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.figure
     *
     * @param figure the value for drawbacks.figure
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setFigure(BigDecimal figure) {
        this.figure = figure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.created_at
     *
     * @return the value of drawbacks.created_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.created_at
     *
     * @param createdAt the value for drawbacks.created_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.updated_at
     *
     * @return the value of drawbacks.updated_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.updated_at
     *
     * @param updatedAt the value for drawbacks.updated_at
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column drawbacks.status
     *
     * @return the value of drawbacks.status
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column drawbacks.status
     *
     * @param status the value for drawbacks.status
     *
     * @mbggenerated Wed Jun 14 15:15:04 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}