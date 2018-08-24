package org.smart4j.chapter1.model;

public class Customer {

    /**
     * 唯一标识id
     */
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户电话
     */
    private String telephone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 性别
     */
    private Boolean sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", sex=" + sex +
                '}';
    }
}
