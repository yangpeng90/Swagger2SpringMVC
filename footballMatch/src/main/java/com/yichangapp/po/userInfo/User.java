package com.yichangapp.po.userInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 用户信息实体
 * @author 杨鹏
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)	// null的字段不显示
@JsonIgnoreProperties(ignoreUnknown=true) // 忽略json中存在而类中不存在的字段
@ApiModel(value="UserInfo")
public class User {
	@ApiModelProperty(value="id", hidden=true)//hidden使得属性不在表单显示，但是还是存在的
    private String id;
	
	@ApiModelProperty(value="QQ号码生成的登陆码", hidden=true)
    private String qqID;

	@ApiModelProperty(value="QQ号码")
    private String qqNum;

	@ApiModelProperty(value="微信号生成的登陆码", hidden=true)
    private String wxID;

	@ApiModelProperty(value="微信号")
    private String wxNum;

	@ApiModelProperty(value="电话号码生成的登陆码", hidden=true)
    private String phoneID;

	@ApiModelProperty(value="电话号码")
    private String phoneNum;

	@ApiModelProperty(value="肖像uri", hidden=true)
    private String portrait;

	@ApiModelProperty(value="昵称")
    private String nickname;

	@ApiModelProperty(value="年龄")
    private Integer age;

	@ApiModelProperty(value="性别")
    private String gender;

	@ApiModelProperty(value="钱包", hidden=true)
    private Integer wallet;

	@ApiModelProperty(value="角色名", hidden=true)
    private String roleName;

	@ApiModelProperty(value="创建时间", hidden=true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss E", timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty(value="积分", hidden=true)
    private Integer accumulation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQqID() {
        return qqID;
    }

    public void setQqID(String qqID) {
        this.qqID = qqID == null ? null : qqID.trim();
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum == null ? null : qqNum.trim();
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID == null ? null : wxID.trim();
    }

    public String getWxNum() {
        return wxNum;
    }

    public void setWxNum(String wxNum) {
        this.wxNum = wxNum == null ? null : wxNum.trim();
    }

    public String getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID == null ? null : phoneID.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss E", timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(Integer accumulation) {
        this.accumulation = accumulation;
    }
}