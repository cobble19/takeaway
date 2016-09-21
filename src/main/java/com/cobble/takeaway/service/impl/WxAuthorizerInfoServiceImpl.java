package com.cobble.takeaway.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.WxAuthorizerInfoMapper;
import com.cobble.takeaway.pojo.HtmlConvertedPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoPOJO;
import com.cobble.takeaway.pojo.weixin.WxAuthorizerInfoSearchPOJO;
import com.cobble.takeaway.service.WxAuthorizerInfoService;
import com.cobble.takeaway.util.FileUtil;

@Service
public class WxAuthorizerInfoServiceImpl implements WxAuthorizerInfoService {
	

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WxAuthorizerInfoMapper wxAuthorizerInfoMapper;

	@Override
	public int insert(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception {
		int ret = 0;
		
		if (StringUtils.isBlank(wxAuthorizerInfoPOJO.getQrcodeFilePath())) {
			// 生成qrcodeFilePath
			String toDir = messageSource.getMessage("files.directory", null, null);
			String toFilePath = "wx/authorizer/qrcode/" + wxAuthorizerInfoPOJO.getAuthorizerAppId() + ".jpg";
			toFilePath = toFilePath.replace("/", File.separator);
			HtmlConvertedPOJO htmlConvertedPOJO = FileUtil.url2File(wxAuthorizerInfoPOJO.getQrcodeUrl(), toDir, toFilePath);
			wxAuthorizerInfoPOJO.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
		}
		
		ret = wxAuthorizerInfoMapper.insert(wxAuthorizerInfoPOJO);
		return ret;
	}

	@Override
	public int update(WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO) throws Exception {
		int ret = 0;

		if (StringUtils.isBlank(wxAuthorizerInfoPOJO.getQrcodeFilePath())) {
			// 生成qrcodeFilePath
			String toDir = messageSource.getMessage("files.directory", null, null);
			String toFilePath = "wx/authorizer/qrcode/" + wxAuthorizerInfoPOJO.getAuthorizerAppId() + ".jpg";
			toFilePath = toFilePath.replace("/", File.separator);
			HtmlConvertedPOJO htmlConvertedPOJO = FileUtil.url2File(wxAuthorizerInfoPOJO.getQrcodeUrl(), toDir, toFilePath);
			wxAuthorizerInfoPOJO.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
		}
		ret = wxAuthorizerInfoMapper.update(wxAuthorizerInfoPOJO);
		return ret;
	}

	@Override
	public List<WxAuthorizerInfoPOJO> finds(
			WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO) throws Exception {
		List<WxAuthorizerInfoPOJO> ret = null;
		ret = wxAuthorizerInfoMapper.finds(wxAuthorizerInfoSearchPOJO);
		
		if (!CollectionUtils.isEmpty(ret)) {
			for (WxAuthorizerInfoPOJO wxAuthorizerInfoPOJO : ret) {
				if (StringUtils.isBlank(wxAuthorizerInfoPOJO.getQrcodeFilePath())) {
					// 生成qrcodeFilePath
					String toDir = messageSource.getMessage("files.directory", null, null);
					String toFilePath = "wx/authorizer/qrcode/" + wxAuthorizerInfoPOJO.getAuthorizerAppId() + ".jpg";
					toFilePath = toFilePath.replace("/", File.separator);
					HtmlConvertedPOJO htmlConvertedPOJO = FileUtil.url2File(wxAuthorizerInfoPOJO.getQrcodeUrl(), toDir, toFilePath);
					wxAuthorizerInfoPOJO.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					WxAuthorizerInfoPOJO temp = new WxAuthorizerInfoPOJO();
					temp.setWxAuthorizerInfoId(wxAuthorizerInfoPOJO.getWxAuthorizerInfoId());
					temp.setQrcodeFilePath(htmlConvertedPOJO.getHtmlPath());
					
					wxAuthorizerInfoMapper.update(temp);
					
				}
			}
			
			
		}
		
		return ret;
	}

	@Override
	public int getCount(WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.getCount(wxAuthorizerInfoSearchPOJO);
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findById(Long id) throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		ret = wxAuthorizerInfoMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = wxAuthorizerInfoMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				ret += wxAuthorizerInfoMapper.deleteById(id);
			}
		}
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findWxAuthorizerInfoByIndexCode(String indexCode)
			throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		ret = wxAuthorizerInfoMapper.findWxAuthorizerInfoByIndexCode(indexCode);
		return ret;
	}

	@Override
	public WxAuthorizerInfoPOJO findWxAuthorizerInfoByUserId(Long userId)
			throws Exception {
		WxAuthorizerInfoPOJO ret = null;
		List<WxAuthorizerInfoPOJO> wxAuthorizerInfoPOJOs = null;
		WxAuthorizerInfoSearchPOJO wxAuthorizerInfoSearchPOJO = new WxAuthorizerInfoSearchPOJO();
		wxAuthorizerInfoSearchPOJO.setUserId(userId);
		wxAuthorizerInfoPOJOs = wxAuthorizerInfoMapper.finds(wxAuthorizerInfoSearchPOJO);
		if (!CollectionUtils.isEmpty(wxAuthorizerInfoPOJOs)) {
			ret = wxAuthorizerInfoPOJOs.get(0);
		}
		return ret;
	}

}
