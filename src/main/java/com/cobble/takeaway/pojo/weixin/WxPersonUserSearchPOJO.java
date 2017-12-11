package com.cobble.takeaway.pojo.weixin;

import java.util.Date;
import java.util.List;

import com.cobble.takeaway.pojo.BaseSearchPOJO;

public class WxPersonUserSearchPOJO extends BaseSearchPOJO {
	private Long wxPersonUserId;
	// 查询关键字
	private String wxAuthorizerUserName;
	private String proxyOpenId;
    private String proxyAuthorizerAppId;
	private Long userId;
    // 
    private String openId;
    // 
    private String authorizerAppId;
    // 用户昵称
    private String nickname;
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    // 语言
    private String language;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headImgUrl;
    // 用户特权信息
    private String privileges;
    
    private String unionId;
    private Integer memberFlag;
    
    private Date createDateTime;
    
    // for search
    private List<Long> userIds;

    public String getProxyAuthorizerAppId() {
		return proxyAuthorizerAppId;
	}

	public void setProxyAuthorizerAppId(String proxyAuthorizerAppId) {
		this.proxyAuthorizerAppId = proxyAuthorizerAppId;
	}

	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Long getWxPersonUserId() {
		return wxPersonUserId;
	}

	public void setWxPersonUserId(Long wxPersonUserId) {
		this.wxPersonUserId = wxPersonUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}

	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

	public String getWxAuthorizerUserName() {
		return wxAuthorizerUserName;
	}

	public void setWxAuthorizerUserName(String wxAuthorizerUserName) {
		this.wxAuthorizerUserName = wxAuthorizerUserName;
	}

	public String getProxyOpenId() {
		return proxyOpenId;
	}

	public void setProxyOpenId(String proxyOpenId) {
		this.proxyOpenId = proxyOpenId;
	}

	public Integer getMemberFlag() {
		return memberFlag;
	}

	public void setMemberFlag(Integer memberFlag) {
		this.memberFlag = memberFlag;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

}