package com.cobble.takeaway.pojo.weixin;

import java.util.Date;

import com.cobble.takeaway.pojo.BasePOJO;

public class WxPersonUserVicePOJO extends BasePOJO {
	private Long wxPersonUserViceId;
	private Long userId;
    // 
    private String openId;
    private String openIdPrimary;
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
    private Integer subscribe;
    private Long subscribeTime;
    private String remark;
    private Integer groupId;
    private String tagidList;
    
    private Date createDateTime;
    
    // 主openid
    private WxPersonUserPOJO wxPersonUserPOJO;
    // 属于哪个公众号
    private WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO;

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

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getTagidList() {
		return tagidList;
	}

	public void setTagidList(String tagidList) {
		this.tagidList = tagidList;
	}

	public String getAuthorizerAppId() {
		return authorizerAppId;
	}

	public void setAuthorizerAppId(String authorizerAppId) {
		this.authorizerAppId = authorizerAppId;
	}

	public Long getWxPersonUserViceId() {
		return wxPersonUserViceId;
	}

	public void setWxPersonUserViceId(Long wxPersonUserViceId) {
		this.wxPersonUserViceId = wxPersonUserViceId;
	}

	public String getOpenIdPrimary() {
		return openIdPrimary;
	}

	public void setOpenIdPrimary(String openIdPrimary) {
		this.openIdPrimary = openIdPrimary;
	}

	public WxPersonUserPOJO getWxPersonUserPOJO() {
		return wxPersonUserPOJO;
	}

	public void setWxPersonUserPOJO(WxPersonUserPOJO wxPersonUserPOJO) {
		this.wxPersonUserPOJO = wxPersonUserPOJO;
	}

	public WxAuthorizerInfoPOJO getWxAuthorizerInfoPOJO() {
		return wxAuthorizerInfoPOJO;
	}

	public void setWxAuthorizerInfoPOJO(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) {
		this.wxAuthorizerInfoPOJO = wxAuthorizerInfoPOJO;
	}

	@Override
	public String toString() {
		return "WxPersonUserVicePOJO [wxPersonUserViceId=" + wxPersonUserViceId
				+ ", userId=" + userId + ", openId=" + openId
				+ ", openIdPrimary=" + openIdPrimary + ", authorizerAppId="
				+ authorizerAppId + ", nickname=" + nickname + ", sex=" + sex
				+ ", language=" + language + ", country=" + country
				+ ", province=" + province + ", city=" + city + ", headImgUrl="
				+ headImgUrl + ", privileges=" + privileges + ", unionId="
				+ unionId + ", subscribe=" + subscribe + ", subscribeTime="
				+ subscribeTime + ", remark=" + remark + ", groupId=" + groupId
				+ ", tagidList=" + tagidList + ", createDateTime="
				+ createDateTime + ", wxPersonUserPOJO=" + wxPersonUserPOJO
				+ ", wxAuthorizerInfoPOJO=" + wxAuthorizerInfoPOJO + "]";
	}
}