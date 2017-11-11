package com.cobble.takeaway.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.WorkbookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ActivityPOJO;
import com.cobble.takeaway.pojo.ActivitySearchPOJO;
import com.cobble.takeaway.pojo.Apply2AttrModelPOJO;
import com.cobble.takeaway.pojo.Apply2AttrPOJO;
import com.cobble.takeaway.pojo.Apply2POJO;
import com.cobble.takeaway.pojo.DataTablesPOJO;
import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.VoteItemPOJO;
import com.cobble.takeaway.pojo.VoteItemParamPOJO;
import com.cobble.takeaway.pojo.VoteItemSearchPOJO;
import com.cobble.takeaway.pojo.VotePOJO;
import com.cobble.takeaway.pojo.VoteSearchPOJO;
import com.cobble.takeaway.service.ActivityService;
import com.cobble.takeaway.service.Apply2AttrModelService;
import com.cobble.takeaway.service.VoteItemService;
import com.cobble.takeaway.service.VoteService;
import com.cobble.takeaway.util.CollectionUtilx;
import com.cobble.takeaway.util.CommonConstant;
import com.cobble.takeaway.util.DateUtil;
import com.cobble.takeaway.util.JsonUtils;
import com.cobble.takeaway.util.UserUtil;

@Controller
public class ActivityController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private Apply2AttrModelService apply2AttrModelService;
	
	@Autowired
	private VoteItemService voteItemService;
	@Autowired
	private VoteService voteService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	

	@RequestMapping(value = "/web/unified/activity/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4WebUnified(ActivityPOJO activityPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityPOJO == null) {
				throw new Exception("activityPOJO can't is NULL.");
			}
			int result = -1;
			if (activityPOJO.getActivityId() != null) {
				result = activityService.update(activityPOJO);
			} else {
				result = activityService.insert(activityPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/unified/activity_detail.jsp?activityId=" + activityPOJO.getActivityId();
		url = "/web/unified/usercenter#create_activity";
		url = "/page/unified/activity_single.jsp";

		redirectStrategy.sendRedirect(request, response, url);
		
//		return ret;
		return null;
	}

	// 版本2中的申请人信息
		@RequestMapping(value = "/api/apply2/v2/export/xls", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
		public Map export4Detail(@RequestParam("activityId") Long activityId
				, @RequestParam("startDateTime") Date startDateTime
				, @RequestParam("endDateTime") Date endDateTime
				, Model model, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			/**
			 * apply2POJOList: List<Map>
			 * columns: List<String>
			 * trHeaderNames
			 */
			Map ret = new HashMap();
			ActivityPOJO activityPOJO = new ActivityPOJO();
			try {
				activityPOJO = activityService.find2ById(activityId, startDateTime, endDateTime);
				logger.info("activityPOJO convert to Json: " + JsonUtils.convertToJson(activityPOJO));
				
				List<Map> maps = new ArrayList<Map>();
				List<String> columns = new ArrayList<String>();
				List<String> trHeaderNames = new ArrayList<String>();
				
				List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
				
				if (!CollectionUtils.isEmpty(apply2AttrModelPOJOs)) {
					for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
						Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
						String key = "attr" + i;
						columns.add(key);
						trHeaderNames.add(apply2AttrModelPOJO.getApply2AttrModelName());
					}
				}
				
				List<Apply2POJO> apply2pojos = activityPOJO.getApply2POJOs();
				if (!CollectionUtils.isEmpty(apply2pojos)) {
					for (int i = 0; i < apply2pojos.size(); i++) {
						Apply2POJO apply2pojo = apply2pojos.get(i);
						Date createDateTime = apply2pojo.getCreateDateTime();
						Map map = new LinkedHashMap();
						List<Apply2AttrPOJO> apply2AttrPOJOs = apply2pojo.getApply2AttrPOJOs();
						if (!CollectionUtils.isEmpty(apply2AttrPOJOs)) {
							int tempLength = apply2AttrPOJOs.size() > columns.size() ? columns.size() : apply2AttrPOJOs.size();
							for (int j = 0; j < tempLength; j++) {
								Apply2AttrPOJO apply2AttrPOJO = apply2AttrPOJOs.get(j);
								String key = columns.get(j);
								map.put(key, apply2AttrPOJO.getApply2AttrData());
							}
							
							if (tempLength < columns.size()) {
								for (int j = tempLength; j < columns.size(); j++) {
									String key = columns.get(j);
									map.put(key, "");
								}
							}
							
							map.put("createDateTime", createDateTime);
							maps.add(map);
						}
					}
				}
				// 放在下面， 上面用到了columns.size()
				trHeaderNames.add("提交时间");
				columns.add("createDateTime");
				
				ret.put("apply2POJOList", maps);
				ret.put("columns", columns);
				ret.put("trHeaderNames", trHeaderNames);
				
				// Export excel
				
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("活动申请人");
				
				HSSFCellStyle cs = wb.createCellStyle();
				HSSFDataFormat df = wb.createDataFormat();
				HSSFFont font = wb.createFont();
				font.setFontHeightInPoints((short)12);
				font.setColor(HSSFColor.BLACK.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				cs.setFont(font);
//				cs.setDataFormat(df.getFormat("#,##0.0"));
				cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
				
				HSSFRow row = sheet.createRow(0);
				for (int i = 0; i < trHeaderNames.size(); i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(trHeaderNames.get(i));
					cell.setCellStyle(cs);
				}
				
				for (int i = 0; i < maps.size(); i++) {
					Map map = maps.get(i);
					row = sheet.createRow(i + 1);
					for (int j = 0; j < columns.size(); j++) {
						HSSFCell cell = row.createCell(j);
						Object value = map.get(columns.get(j));
						if (value instanceof Date) {
							String dateStr = DateUtil.toStr((Date) value, "yyyy-MM-dd HH:mm:ss");
							cell.setCellValue(dateStr);
						} else {
							cell.setCellValue(value != null ? value.toString() : "");
						}
					}
				}
				
				/*FileOutputStream os = new FileOutputStream("apply2.xls");
				wb.write(os);
				os.close();*/
				
				String fileName = WorkbookUtil.createSafeSheetName(activityPOJO.getTitle());
				fileName = URLEncoder.encode(fileName, "UTF-8");
				fileName = fileName + ".xls";
				
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//				response.setContentType("application/octet-stream");
				response.setContentType("application/ms-excel");
				OutputStream out = response.getOutputStream();

	            BufferedOutputStream bos = new BufferedOutputStream(out);   
				wb.write(bos);
				bos.flush();
				bos.close();
				
				
			} catch (Exception e) {
				logger.error("query error.", e);
				throw e;
			}
			
//			return ret;
			return null;
		}
		
	// 版本2中的申请人信息
	@RequestMapping(value = "/api/apply2/v2/export/xls/vote", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public Map export4Detail4Vote(@RequestParam("activityId") Long activityId
			, @RequestParam("startDateTime") Date startDateTime
			, @RequestParam("endDateTime") Date endDateTime
			, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * apply2POJOList: List<Map>
		 * columns: List<String>
		 * trHeaderNames
		 */
		Map ret = new HashMap();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		try {
			/*activityPOJO = activityService.find2ById(activityId);*/
			activityPOJO = activityService.find2ById(activityId, startDateTime, endDateTime);
			logger.info("activityPOJO convert to Json: " + JsonUtils.convertToJson(activityPOJO));
			
			long voteId = -99999;
			VoteSearchPOJO voteSearchPOJO = new VoteSearchPOJO();
			voteSearchPOJO.setActivityId(activityId);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(votePOJOs)) {
				voteId = votePOJOs.get(0).getVoteId();
			}
			
			List<Map> maps = new ArrayList<Map>();
			List<String> columns = new ArrayList<String>();
			List<String> trHeaderNames = new ArrayList<String>();
			
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
			
			if (!CollectionUtils.isEmpty(apply2AttrModelPOJOs)) {
				for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
					Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
					String key = "attr" + i;
					columns.add(key);
					trHeaderNames.add(apply2AttrModelPOJO.getApply2AttrModelName());
				}
			}
			
			List<Apply2POJO> apply2pojos = activityPOJO.getApply2POJOs();
			
			/*List<Long> apply2Ids = new ArrayList<Long>();*/
			
			
			if (!CollectionUtils.isEmpty(apply2pojos)) {
				/*for (int i = 0; i < apply2pojos.size(); i++) {
					apply2Ids.add(apply2pojos.get(i).getApply2Id());
				}*/
				VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
//				voteItemSearchPOJO.setApply2Ids(apply2Ids);
				voteItemSearchPOJO.setVoteId(voteId);
				voteItemSearchPOJO.setPaginationFlage(false);
				// Map<apply2Id, >
				Map<Long, VoteItemPOJO> voteItemMaps = voteItemService.finds4Map(voteItemSearchPOJO);
				
				for (int i = 0; i < apply2pojos.size(); i++) {	// an apply2 VS a record
					Apply2POJO apply2pojo = apply2pojos.get(i);
					Date createDateTime = apply2pojo.getCreateDateTime();
					Map map = new LinkedHashMap();
					List<Apply2AttrPOJO> apply2AttrPOJOs = apply2pojo.getApply2AttrPOJOs();
					if (!CollectionUtils.isEmpty(apply2AttrPOJOs)) {
						int tempLength = apply2AttrPOJOs.size() > columns.size() ? columns.size() : apply2AttrPOJOs.size();
						for (int j = 0; j < tempLength; j++) {
							Apply2AttrPOJO apply2AttrPOJO = apply2AttrPOJOs.get(j);
							String key = columns.get(j);
							map.put(key, apply2AttrPOJO.getApply2AttrData());
						}
						
						if (tempLength < columns.size()) {
							for (int j = tempLength; j < columns.size(); j++) {
								String key = columns.get(j);
								map.put(key, "");
							}
						}
						
						map.put("createDateTime", createDateTime);
						VoteItemPOJO voteItemPOJOTemp = voteItemMaps.get(apply2pojo.getApply2Id());
						Long voteItemId = null;
						Integer approveFlag = 0;
						Integer totalNum = 0;
						if (voteItemPOJOTemp != null) {
							voteItemId = voteItemPOJOTemp.getVoteItemId();
							approveFlag = voteItemPOJOTemp.getApproveFlag();
							totalNum = voteItemPOJOTemp.getTotalNum();
						}
						map.put("voteItemId", voteItemId);
						map.put("approveFlag", approveFlag);
						map.put("totalNum", totalNum);
						map.put("apply2Id", apply2pojo.getApply2Id());
						maps.add(map);
					}
				}
			}
			// 放在下面， 上面用到了columns.size()
			trHeaderNames.add("提交时间");
			columns.add("createDateTime");
			trHeaderNames.add("审批投票项");
			columns.add("voteItemId");
			trHeaderNames.add("审批标志");
			columns.add("approveFlag");
			trHeaderNames.add("票数");
			columns.add("totalNum");
			trHeaderNames.add("APPLY2 ID");
			columns.add("apply2Id");
			
			ret.put("apply2POJOList", maps);
			ret.put("columns", columns);
			ret.put("trHeaderNames", trHeaderNames);
			
			// Export excel
			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("活动申请人");
			
			HSSFCellStyle cs = wb.createCellStyle();
			HSSFDataFormat df = wb.createDataFormat();
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short)12);
			font.setColor(HSSFColor.BLACK.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			cs.setFont(font);
//			cs.setDataFormat(df.getFormat("#,##0.0"));
			cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
			
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < trHeaderNames.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(trHeaderNames.get(i));
				cell.setCellStyle(cs);
			}
			
			for (int i = 0; i < maps.size(); i++) {
				Map map = maps.get(i);
				row = sheet.createRow(i + 1);
				for (int j = 0; j < columns.size(); j++) {
					HSSFCell cell = row.createCell(j);
					Object value = map.get(columns.get(j));
					if (value instanceof Date) {
						String dateStr = DateUtil.toStr((Date) value, "yyyy-MM-dd HH:mm:ss");
						cell.setCellValue(dateStr);
					} else {
						cell.setCellValue(value != null ? value.toString() : "");
					}
				}
			}
			
			/*FileOutputStream os = new FileOutputStream("apply2.xls");
			wb.write(os);
			os.close();*/
			
			String fileName = WorkbookUtil.createSafeSheetName(activityPOJO.getTitle());
			fileName = URLEncoder.encode(fileName, "UTF-8");
			fileName = fileName + ".xls";
			
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//			response.setContentType("application/octet-stream");
			response.setContentType("application/ms-excel");
			OutputStream out = response.getOutputStream();

            BufferedOutputStream bos = new BufferedOutputStream(out);   
			wb.write(bos);
			bos.flush();
			bos.close();
			
			
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
//		return ret;
		return null;
	}
	

	@RequestMapping(value = "/api/apply2/v2/{activityId}/vote/settingfilter4vote", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map settingFilter4Vote(@PathVariable("activityId") Long activityId
			, @RequestParam(value="apply2AttrModelIds", required=false) String apply2AttrModelIds
			, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		try {
			Long userId = UserUtil.getCurrentUserId();

			activityPOJO = activityService.find2ById(activityId);
			
			VoteSearchPOJO voteSearchPOJO = new VoteSearchPOJO();
			voteSearchPOJO.setActivityId(activityId);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			VotePOJO votePOJO = null;
			if (!CollectionUtils.isEmpty(votePOJOs)) {
				votePOJO = votePOJOs.get(0);
			}
			
			if (votePOJO == null) {
				logger.error("votePOJO is null");
				// insert vote
				votePOJO = new VotePOJO();
				votePOJO.setActivityId(activityId);
				votePOJO.setApply2AttrModelIds(apply2AttrModelIds);
				votePOJO.setUserId(userId);
				votePOJO.setContent(activityPOJO.getContent());
				votePOJO.setTitle(activityPOJO.getTitle());
				votePOJO.setVoteType(2);
				votePOJO.setPublishType(1);
				voteService.insert(votePOJO);
			} else {
				votePOJO.setApply2AttrModelIds(apply2AttrModelIds);
				voteService.update(votePOJO);
			}
			
			ret.put("success", true);
			
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	// if voteItemId is null, create voteItem
	// else delete voteItemId
	@RequestMapping(value = "/api/apply2/v2/{activityId}/vote/approve", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map query4ApproveToVoteItem(
			VoteItemParamPOJO voteItemParamPOJO
			, @PathVariable("activityId") Long activityId
			/*, @RequestParam(value="apply2Id") Long apply2Id
			, @RequestParam(value="voteItemId", required=false) Long voteItemId*/
			, @RequestParam(value="apply2AttrModelIds", required=false) String apply2AttrModelIds
			, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map ret = new HashMap();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		try {
			List<VoteItemPOJO> voteItemPOJOs = voteItemParamPOJO.getVoteItemPOJOs();
			
			Long userId = UserUtil.getCurrentUserId();

			activityPOJO = activityService.find2ById(activityId);
			
			VoteSearchPOJO voteSearchPOJO = new VoteSearchPOJO();
			voteSearchPOJO.setActivityId(activityId);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			VotePOJO votePOJO = null;
			if (!CollectionUtils.isEmpty(votePOJOs)) {
				votePOJO = votePOJOs.get(0);
			}
			
//			VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
//			voteItemSearchPOJO.setApply2Id(apply2Id);
			
//			List<VoteItemPOJO> voteItems = voteItemService.finds(voteItemSearchPOJO);
			
			if (votePOJO == null) {
				logger.error("votePOJO is null");
				// insert vote
				votePOJO = new VotePOJO();
				votePOJO.setActivityId(activityId);
				votePOJO.setApply2AttrModelIds(apply2AttrModelIds);
				votePOJO.setUserId(userId);
				votePOJO.setContent(activityPOJO.getContent());
				votePOJO.setTitle(activityPOJO.getTitle());
				votePOJO.setVoteType(2);
				votePOJO.setPublishType(1);
				voteService.insert(votePOJO);
			}
			
			
			for (int i = 0; i < voteItemPOJOs.size(); i++) {
				VoteItemPOJO temp = voteItemPOJOs.get(i);
				Long voteItemId = temp.getVoteItemId();
				Long apply2Id = temp.getApply2Id();
				Integer approveFlag = temp.getApproveFlag();
				if (voteItemId == null) {
					VoteItemPOJO voteItemPOJO = new VoteItemPOJO();
					voteItemPOJO.setApply2Id(apply2Id);
					voteItemPOJO.setDescription("create by back code");
					voteItemPOJO.setTotalNum(0);
					voteItemPOJO.setApproveFlag(1);
					voteItemPOJO.setVoteId(votePOJO.getVoteId());
					
					voteItemService.insert(voteItemPOJO, userId);
				} else {
					// ----- delete, un approved --- to update
					VoteItemPOJO voteItemPOJO = new VoteItemPOJO();
					if (approveFlag == null || approveFlag == 0) {
						voteItemPOJO.setVoteItemId(voteItemId);
						voteItemPOJO.setApproveFlag(1);
					} else {
						voteItemPOJO.setVoteItemId(voteItemId);
						voteItemPOJO.setApproveFlag(0);
					}
					voteItemService.update(voteItemPOJO);
				}
			}
			
			ret.put("success", true);
			
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/unified/apply2inactivity/vote")
	public ModelAndView apply2InActivity4Vote(@RequestParam(value="activityId", required=false) Long activityId, 
			@RequestParam(value="activityTitle", required=false) String activityTitle
			, @RequestParam(value="startDateTime", required=false) Date startDateTime
			, @RequestParam(value="endDateTime", required=false) Date endDateTime
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {

//			ActivityPOJO activityPOJO = new ActivityPOJO();
//			activityPOJO = activityService.find2ById(activityId, startDateTime, endDateTime);
			

			VoteSearchPOJO voteSearchPOJO = new VoteSearchPOJO();
			voteSearchPOJO.setActivityId(activityId);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			VotePOJO votePOJO = null;
			String apply2AttrModelIds = "";
			List<Long> apply2AttrModelIdList = new ArrayList<Long>();
			if (!CollectionUtils.isEmpty(votePOJOs)) {
				votePOJO = votePOJOs.get(0);
				apply2AttrModelIds = votePOJO.getApply2AttrModelIds();
				apply2AttrModelIdList = CollectionUtilx.string2Longs(apply2AttrModelIds);
			}
			
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
			if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(apply2AttrModelPOJOs) 
					&& org.apache.commons.collections4.CollectionUtils.isNotEmpty(apply2AttrModelIdList)) {
				for (Apply2AttrModelPOJO apply2AttrModelPOJO : apply2AttrModelPOJOs) {
					if (apply2AttrModelIdList.contains(apply2AttrModelPOJO.getApply2AttrModelId())) {
						apply2AttrModelPOJO.setChecked(true);
					}
				}
			}
			
			
			ret.addObject("apply2AttrModelPOJOs", apply2AttrModelPOJOs);
			ret.setViewName("/page/unified/apply2_in_activity_vote");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	// 版本2中的申请人信息
	@RequestMapping(value = "/api/apply2/v2/{activityId}/vote", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map query4Detail4Vote(@PathVariable("activityId") Long activityId
			, @RequestParam(value="startDateTime", required=false) Date startDateTime
			, @RequestParam(value="endDateTime", required=false) Date endDateTime
			, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * apply2POJOList: List<Map>
		 * columns: List<String>
		 * trHeaderNames
		 */
		Map ret = new HashMap();
		ActivityPOJO activityPOJO = new ActivityPOJO();
		try {
			/*activityPOJO = activityService.find2ById(activityId);*/
			activityPOJO = activityService.find2ById(activityId, startDateTime, endDateTime);
			logger.info("activityPOJO convert to Json: " + JsonUtils.convertToJson(activityPOJO));
			
			long voteId = -99999;
			VoteSearchPOJO voteSearchPOJO = new VoteSearchPOJO();
			voteSearchPOJO.setActivityId(activityId);
			List<VotePOJO> votePOJOs = voteService.finds(voteSearchPOJO);
			if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(votePOJOs)) {
				voteId = votePOJOs.get(0).getVoteId();
			}
			
			List<Map> maps = new ArrayList<Map>();
			List<String> columns = new ArrayList<String>();
			List<String> trHeaderNames = new ArrayList<String>();
			
			List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
			
			if (!CollectionUtils.isEmpty(apply2AttrModelPOJOs)) {
				for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
					Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
					String key = "attr" + i;
					columns.add(key);
					trHeaderNames.add(apply2AttrModelPOJO.getApply2AttrModelName());
				}
			}
			
			List<Apply2POJO> apply2pojos = activityPOJO.getApply2POJOs();
			
			/*List<Long> apply2Ids = new ArrayList<Long>();*/
			
			
			if (!CollectionUtils.isEmpty(apply2pojos)) {
				/*for (int i = 0; i < apply2pojos.size(); i++) {
					apply2Ids.add(apply2pojos.get(i).getApply2Id());
				}*/
				VoteItemSearchPOJO voteItemSearchPOJO = new VoteItemSearchPOJO();
//				voteItemSearchPOJO.setApply2Ids(apply2Ids);
				voteItemSearchPOJO.setVoteId(voteId);
				voteItemSearchPOJO.setPaginationFlage(false);
				// Map<apply2Id, >
				Map<Long, VoteItemPOJO> voteItemMaps = voteItemService.finds4Map(voteItemSearchPOJO);
				
				for (int i = 0; i < apply2pojos.size(); i++) {	// an apply2 VS a record
					Apply2POJO apply2pojo = apply2pojos.get(i);
					Date createDateTime = apply2pojo.getCreateDateTime();
					Map map = new LinkedHashMap();
					List<Apply2AttrPOJO> apply2AttrPOJOs = apply2pojo.getApply2AttrPOJOs();
					if (!CollectionUtils.isEmpty(apply2AttrPOJOs)) {
						int tempLength = apply2AttrPOJOs.size() > columns.size() ? columns.size() : apply2AttrPOJOs.size();
						for (int j = 0; j < tempLength; j++) {
							Apply2AttrPOJO apply2AttrPOJO = apply2AttrPOJOs.get(j);
							String key = columns.get(j);
							map.put(key, apply2AttrPOJO.getApply2AttrData());
						}
						
						if (tempLength < columns.size()) {
							for (int j = tempLength; j < columns.size(); j++) {
								String key = columns.get(j);
								map.put(key, "");
							}
						}
						
						map.put("createDateTime", createDateTime);
						VoteItemPOJO voteItemPOJOTemp = voteItemMaps.get(apply2pojo.getApply2Id());
						Long voteItemId = null;
						Integer approveFlag = 0;
						Integer totalNum = 0;
						if (voteItemPOJOTemp != null) {
							voteItemId = voteItemPOJOTemp.getVoteItemId();
							approveFlag = voteItemPOJOTemp.getApproveFlag();
							totalNum = voteItemPOJOTemp.getTotalNum();
						}
						map.put("voteItemId", voteItemId);
						map.put("approveFlag", approveFlag);
						map.put("totalNum", totalNum);
						map.put("apply2Id", apply2pojo.getApply2Id());
						maps.add(map);
					}
				}
			}
			// 放在下面， 上面用到了columns.size()
			trHeaderNames.add("提交时间");
			columns.add("createDateTime");
			trHeaderNames.add("审批投票项");
			columns.add("voteItemId");
			trHeaderNames.add("审批标志");
			columns.add("approveFlag");
			trHeaderNames.add("票数");
			columns.add("totalNum");
			trHeaderNames.add("APPLY2 ID");
			columns.add("apply2Id");
			
			ret.put("apply2POJOList", maps);
			ret.put("columns", columns);
			ret.put("trHeaderNames", trHeaderNames);
			
			ret.put("apply2AttrModelPOJOs", apply2AttrModelPOJOs);
			
			ret.put("activityPOJO", activityPOJO);
			
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	// 版本2中的申请人信息
		@RequestMapping(value = "/api/apply2/v2/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE})
		@ResponseBody
		public Map query4Detail(@PathVariable("activityId") Long activityId
				, @RequestParam(value="startDateTime", required=false) Date startDateTime
				, @RequestParam(value="endDateTime", required=false) Date endDateTime
				, Model model, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			/**
			 * apply2POJOList: List<Map>
			 * columns: List<String>
			 * trHeaderNames
			 */
			Map ret = new HashMap();
			ActivityPOJO activityPOJO = new ActivityPOJO();
			try {
				/*activityPOJO = activityService.find2ById(activityId);*/
				activityPOJO = activityService.find2ById(activityId, startDateTime, endDateTime);
				logger.info("activityPOJO convert to Json: " + JsonUtils.convertToJson(activityPOJO));
				
				List<Map> maps = new ArrayList<Map>();
				List<String> columns = new ArrayList<String>();
				List<String> trHeaderNames = new ArrayList<String>();
				
				List<Apply2AttrModelPOJO> apply2AttrModelPOJOs = apply2AttrModelService.findsByActivityId(activityId);
				
				if (!CollectionUtils.isEmpty(apply2AttrModelPOJOs)) {
					for (int i = 0; i < apply2AttrModelPOJOs.size(); i++) {
						Apply2AttrModelPOJO apply2AttrModelPOJO = apply2AttrModelPOJOs.get(i);
						String key = "attr" + i;
						columns.add(key);
						trHeaderNames.add(apply2AttrModelPOJO.getApply2AttrModelName());
					}
				}
				
				List<Apply2POJO> apply2pojos = activityPOJO.getApply2POJOs();
				
				List<Long> apply2Ids = new ArrayList<Long>();
				
				if (!CollectionUtils.isEmpty(apply2pojos)) {
					for (int i = 0; i < apply2pojos.size(); i++) {
						apply2Ids.add(apply2pojos.get(i).getApply2Id());
					}
					
					for (int i = 0; i < apply2pojos.size(); i++) {	// an apply2 VS a record
						Apply2POJO apply2pojo = apply2pojos.get(i);
						Date createDateTime = apply2pojo.getCreateDateTime();
						Map map = new LinkedHashMap();
						List<Apply2AttrPOJO> apply2AttrPOJOs = apply2pojo.getApply2AttrPOJOs();
						if (!CollectionUtils.isEmpty(apply2AttrPOJOs)) {
							int tempLength = apply2AttrPOJOs.size() > columns.size() ? columns.size() : apply2AttrPOJOs.size();
							for (int j = 0; j < tempLength; j++) {
								Apply2AttrPOJO apply2AttrPOJO = apply2AttrPOJOs.get(j);
								String key = columns.get(j);
								map.put(key, apply2AttrPOJO.getApply2AttrData());
							}
							
							if (tempLength < columns.size()) {
								for (int j = tempLength; j < columns.size(); j++) {
									String key = columns.get(j);
									map.put(key, "");
								}
							}
							
							map.put("createDateTime", createDateTime);
							maps.add(map);
						}
					}
				}
				// 放在下面， 上面用到了columns.size()
				trHeaderNames.add("提交时间");
				columns.add("createDateTime");
				
				//前台页面用不到内容, 而且内容size大, 占用带宽
				activityPOJO.setContent("");
				
				ret.put("apply2POJOList", maps);
				ret.put("columns", columns);
				ret.put("trHeaderNames", trHeaderNames);
				ret.put("activityPOJO", activityPOJO);
				
			} catch (Exception e) {
				logger.error("query error.", e);
				throw e;
			}
			
			return ret;
		}

	@RequestMapping(value = "/api/unified/activity/publishType", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO publishType(ActivityPOJO activityPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityPOJO == null) {
				throw new Exception("activityPOJO can't is NULL.");
			}
			if (activityPOJO.getActivityId() == null || activityPOJO.getPublishType() == null) {
				throw new Exception("activityId/publishType can't is NULL. activityId: " + activityPOJO.getActivityId() + ", publishType: " + activityPOJO.getPublishType());
			}
			int result = -1;
			
			result = activityService.update(activityPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/api/enterprise/activity/addOrUpdate", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add4WebAPI(ActivityPOJO activityPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityPOJO == null) {
				throw new Exception("activityPOJO can't is NULL.");
			}
			int result = -1;
			if (activityPOJO.getActivityId() != null) {
				result = activityService.update(activityPOJO);
			} else {
				result = activityService.insert(activityPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activity/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public StatusPOJO add4Web(ActivityPOJO activityPOJO, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			if (activityPOJO == null) {
				throw new Exception("activityPOJO can't is NULL.");
			}
			int result = -1;
			if (activityPOJO.getActivityId() != null) {
				result = activityService.update(activityPOJO);
			} else {
				result = activityService.insert(activityPOJO, UserUtil.getCurrentUserId());
			}
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		String url = "/page/enterprise/activity_detail.jsp?activityId=" + activityPOJO.getActivityId();
		redirectStrategy.sendRedirect(request, response, url);;
		
//		return ret;
		return null;
	}
	
	@RequestMapping(value = "/web/enterprise/activity/{activityId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ActivityPOJO query(@PathVariable("activityId") Long activityId, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActivityPOJO ret = new ActivityPOJO();
		try {
			ret = activityService.findById(activityId);
			if (ret != null && ret.getWxAuthorizerInfoPOJO() != null) {
				String authorizerAppId = ret.getWxAuthorizerInfoPOJO().getAuthorizerAppId();
				HttpSession session = request.getSession();
				session.setAttribute(CommonConstant.AUTHORIZER_APP_ID, authorizerAppId);
			}
		} catch (Exception e) {
			logger.error("query error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activity/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> query(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		try {
			List<ActivityPOJO> activityPOJOs = activityService.finds(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/enterprise/provider/activity", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> queryProvider(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		try {
			if (UserUtil.getCurrentUserId() == null) {
				return ret;
			}
			activitySearchPOJO.setUserId(UserUtil.getCurrentUserId());
			List<ActivityPOJO> activityPOJOs = activityService.find4Enterprises(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/unified/activity/list/active/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView queryActive4PageUnified(@PathVariable(value = "userId") Long userId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			ActivitySearchPOJO activitySearchPOJO = new ActivitySearchPOJO();
			activitySearchPOJO.setUserId(userId);
			List<ActivityPOJO> activityPOJOs = activityService.findActives(activitySearchPOJO);
			
			if (!CollectionUtils.isEmpty(activityPOJOs)) {
				String documentTitle = StringUtils.isNotBlank(activityPOJOs.get(0).getUserPOJO().getNickname()) ? 
						activityPOJOs.get(0).getUserPOJO().getNickname() : activityPOJOs.get(0).getUserPOJO().getUsername();
				ret.addObject("documentTitle", documentTitle);
			}
			ret.addObject("activityPOJOs", activityPOJOs);
			ret.setViewName("/page/unified/activity_active");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	/*@RequestMapping(value = "/web/enterprise/activity/list/active/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView queryActive4Page(@PathVariable(value = "userId") Long userId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView();
		try {
			ActivitySearchPOJO activitySearchPOJO = new ActivitySearchPOJO();
			activitySearchPOJO.setUserId(userId);
			List<ActivityPOJO> activityPOJOs = activityService.findActives(activitySearchPOJO);
			
			if (!CollectionUtils.isEmpty(activityPOJOs)) {
				String documentTitle = StringUtils.isNotBlank(activityPOJOs.get(0).getUserPOJO().getNickname()) ? 
						activityPOJOs.get(0).getUserPOJO().getNickname() : activityPOJOs.get(0).getUserPOJO().getUsername();
				ret.addObject("documentTitle", documentTitle);
			}
			ret.addObject("activityPOJOs", activityPOJOs);
			ret.setViewName("/page/enterprise/activity_active");
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}*/
	
	@RequestMapping(value = "/web/enterprise/activity/list/active", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> queryActive(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		try {
			List<ActivityPOJO> activityPOJOs = activityService.findActives(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			logger.error("list error.", e);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/web/enterprise/activityByUserId", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DataTablesPOJO<ActivityPOJO> queryByUserId(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		DataTablesPOJO<ActivityPOJO> ret = new DataTablesPOJO<ActivityPOJO>();
		activitySearchPOJO.setUserId(UserUtil.getCurrentUserId());
		try {
			activitySearchPOJO.setPaginationFlage(false);
			List<ActivityPOJO> activityPOJOs = activityService.finds(activitySearchPOJO);
			ret.setData(activityPOJOs);
		} catch (Exception e) {
			logger.error("activityByUserId error.", e);
			throw e;
		}
		
		return ret;
	}

	@RequestMapping(value = "/web/activity/all", method = {RequestMethod.GET})
	public ModelAndView findFoodSellersPure(ActivitySearchPOJO activitySearchPOJO) throws Exception {
		ModelAndView ret = new ModelAndView();
		List<ActivityPOJO> activityPOJOList = new ArrayList<ActivityPOJO>();
		activityPOJOList = activityService.finds(activitySearchPOJO);
//		int total = activityService.getCount(activitySearchPOJO);
		ret.addObject("activityPOJOList", activityPOJOList);
		ret.setViewName("page/activity_all");
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<ActivityPOJO> findActivity(ActivitySearchPOJO activitySearchPOJO, Model model) throws Exception {
		ExtjsPOJO<ActivityPOJO> ret = new ExtjsPOJO<ActivityPOJO>();
		List<ActivityPOJO> activityPOJOList = new ArrayList<ActivityPOJO>();
		activityPOJOList = activityService.finds(activitySearchPOJO);
		int total = activityService.getCount(activitySearchPOJO);
		
		ret.setGridModelList(activityPOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		model.addAttribute("activityPOJOList", activityPOJOList);
		model.addAttribute("success", true);
		model.addAttribute("total", 1);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(ActivityPOJO activityPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		
		try {
			int result = activityService.insert(activityPOJO, UserUtil.getCurrentUserId());
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(ActivityPOJO activityPOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.update(activityPOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/activity/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(@RequestParam("ids") Long[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = activityService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			logger.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
