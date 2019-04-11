package com.controller;

import com.config.URLConstant;
import com.dao.VoMapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.entity.Dd_Info_Dic;
import com.entity.Dd_Operation;
import com.entity.Dd_User;
import com.entity.Dd_User_Quyu_Per;
import com.entity.Model;
import com.quyu.Dq;
import com.quyu.Pq;
import com.quyu.Zq;
import com.servicecode.CommonService;
import com.taobao.api.ApiException;
import com.util.AccessTokenUtil;
import com.util.ServiceResult;
import net.sf.json.JSONObject;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * 企业内部E应用Quick-Start示例代码 实现了最简单的免密登录（免登）功能
 */
@RestController
@EnableAutoConfiguration
public class IndexController {
	private static final Logger bizLogger = LoggerFactory.getLogger(IndexController.class);
	@Resource
	private VoMapper voMapper;

	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 欢迎页面,通过url访问，判断后端服务是否启动
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult login(@RequestParam(value = "authCode") String requestAuthCode) {
		ServiceResult serviceResult = null;
		String userId=null;
		try {
			// 获取accessToken,注意正是代码要有异常流处理
			String accessToken = AccessTokenUtil.getToken();
			// 获取用户信息
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
			OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
			request.setCode(requestAuthCode);
			request.setHttpMethod("GET");
			OapiUserGetuserinfoResponse response;
			response = client.execute(request, accessToken);
			// 3.查询得到当前用户的userId
			// 获得到userId之后应用应该处理应用自身的登录会话管理（session），避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
			userId = response.getUserid();
			OapiUserGetResponse userProfile = getUserProfile(accessToken, userId);
			int count=voMapper.countOperation(userId);
			String userName = userProfile.getName();
			Long deptId = userProfile.getDepartment().get(0);
			String email = userProfile.getEmail();
			String mobile = userProfile.getMobile();
			String remark = userProfile.getRemark();
			Map<String, Object> resultMap = new HashMap<>();
			if(count==0) {
				resultMap.put("isfirst", true);
			}else {
				resultMap.put("isfirst", false);
			}
			resultMap.put("email", email);
			resultMap.put("mobile", mobile);
			resultMap.put("remark", remark);
			resultMap.put("email", email);
			resultMap.put("name", userName);
			resultMap.put("userid", userId);
			resultMap.put("removed", "0");
			if (deptId.longValue() == 1L) {// 审批里的部门id，1和-1要互相转换一下
				deptId = -1L;
			}
			resultMap.put("deptId", deptId);
			resultMap.put("department", deptId);
			Dd_User user = voMapper.getUser(userId);
			if (user == null) {
				voMapper.putUser(resultMap);
			}
			List<Dd_User_Quyu_Per> list = voMapper.getPermission(userId);
			if (list.size() == 0) {
				resultMap.put("quanxian", false);
			} else {
				resultMap.put("quanxian", true);
			}
			logger.info("返回数据：" + resultMap.toString());
			Dd_Operation op=Dd_Operation.getInstance(userId, 15, accessToken, "true", "");
			voMapper.addOperation(op);//添加登录日志到数据库
			serviceResult=ServiceResult.success(resultMap);
		} catch (ApiException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			Dd_Operation op=Dd_Operation.getInstance(userId, 15, "", "false", e.getMessage());
			voMapper.addOperation(op);//添加登录日志到数据库
			return ServiceResult.failure("0", e.getMessage());
		}
		return serviceResult;
	}

	/**
	 * 获取用户详情
	 *
	 * @param accessToken
	 * @param userId
	 * @return
	 * @throws ApiException
	 */
	private OapiUserGetResponse getUserProfile(String accessToken, String userId) throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
		OapiUserGetRequest request = new OapiUserGetRequest();
		request.setUserid(userId);
		request.setHttpMethod("GET");
		OapiUserGetResponse response = client.execute(request, accessToken);
		return response;

	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public Map query(@RequestParam Map para) {
		String xh = (String) para.get("xh");
		String userid = (String) para.get("userid");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 请求接口获取今日和本月数据
			JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
			jwpfb.setServiceClass(CommonService.class);
			InputStream is = IndexController.class.getClassLoader().getResourceAsStream("para.properties");
			Properties pro = new Properties();
			pro.load(is);
			String webServiceURL = pro.getProperty("webServiceURL");
			jwpfb.setAddress(webServiceURL);
			CommonService hw = (CommonService) jwpfb.create();
			String res = "{endTime:\"\",startTime:\"\",xh:\"" + xh + "\"}";
			String json = hw.getQueryData(res);
			JSONObject jsonObject = JSONObject.fromObject(json);
			Map<String,Object> classMap = new HashMap<String,Object>();
			classMap.put("dayData", Pq.class);
			classMap.put("monthData", Pq.class);
			Model stu = (Model) JSONObject.toBean(jsonObject, Model.class, classMap);
			Boolean success=stu.getSuccess();
			if(success) {
				List<Pq> listmonth = (List<Pq>) stu.getMonthData();
				List<Pq> listday = (List<Pq>) stu.getDayData();
				Map<String,Pq> monthData = new HashMap<String,Pq>();
				Map<String,Pq> dayData = new HashMap<String,Pq>();
				List<Dd_User_Quyu_Per> pers = voMapper.getPermission(userid);// 获取用户权限数据
				for (Dd_User_Quyu_Per duqp : pers) {// 筛选权限数据
					for (Pq vo : listmonth) {
						if (vo.getName_code().equals(duqp.getPermission())) {
							vo.setName(duqp.getName());
							monthData.put(vo.getName_code(), vo);
						}
					}
					Boolean monthHave=monthData.containsKey(duqp.getPermission());
					if(!monthHave) {
						Pq pq=new Pq();
						pq.setName(duqp.getName());
						pq.setName_code(duqp.getPermission());
						pq.setShuliang("0");
						pq.setXiaoliang("0");
						monthData.put(duqp.getPermission(), pq);
					}
					for (Pq vo : listday) {
						if (vo.getName_code().equals(duqp.getPermission())) {
							vo.setName(duqp.getName());
							dayData.put(vo.getName_code(), vo);
						}
					}
					Boolean dayHave=dayData.containsKey(duqp.getPermission());
					if(!dayHave) {
						Pq pq=new Pq();
						pq.setName(duqp.getName());
						pq.setName_code(duqp.getPermission());
						pq.setShuliang("0");
						pq.setXiaoliang("0");
						dayData.put(duqp.getPermission(), pq);
					}
				}
				List<Zq> dayZqList=new ArrayList<Zq>();
				List<Zq> monthZqList=new ArrayList<Zq>();
				Map<String,String> getZq=new HashMap<String,String>();
				getZq.put("code_type", "XLQY1");
				List<Dd_Info_Dic> zqs=voMapper.getDic_Info(getZq);
				for(Dd_Info_Dic zqdic:zqs){
					Zq zq=new Zq();
					zq.setName(zqdic.getName());
					zq.setName_code(zqdic.getCode());
					List<Dq> dqs=new ArrayList<Dq>();
					Map<String,String> getDq=new HashMap<String,String>();
					getDq.put("father_code", zqdic.getCode());
					List<Dd_Info_Dic> dqsdic=voMapper.getDic_Info(getDq);
					for(Dd_Info_Dic dqdic:dqsdic) {
						Dq dq=new Dq();
						dq.setName(dqdic.getName());
						dq.setName_code(dqdic.getCode());
						List<Pq> pqs=new ArrayList<Pq>();
						Map<String,String> getPq=new HashMap<String,String>();
						getPq.put("father_code",dqdic.getCode());
						List<Dd_Info_Dic> pqsdic=voMapper.getDic_Info(getPq);
						for(Dd_Info_Dic pqidc:pqsdic) {
							Pq pq=dayData.get(pqidc.getCode());
							pqs.add(pq);
						}
						dq.setPq(pqs);
						dqs.add(dq);
					}
					zq.setDq(dqs);
					dayZqList.add(zq);
				}
				for(Dd_Info_Dic zqdic:zqs){
					Zq zq=new Zq();
					zq.setName(zqdic.getName());
					zq.setName_code(zqdic.getCode());
					List<Dq> dqs=new ArrayList<Dq>();
					Map<String,String> getDq=new HashMap<String,String>();
					getDq.put("father_code", zqdic.getCode());
					List<Dd_Info_Dic> dqsdic=voMapper.getDic_Info(getDq);
					for(Dd_Info_Dic dqdic:dqsdic) {
						Dq dq=new Dq();
						dq.setName(dqdic.getName());
						dq.setName_code(dqdic.getCode());
						List<Pq> pqs=new ArrayList<Pq>();
						Map<String,String> getPq=new HashMap<String,String>();
						getPq.put("father_code",dqdic.getCode());
						List<Dd_Info_Dic> pqsdic=voMapper.getDic_Info(getPq);
						for(Dd_Info_Dic pqidc:pqsdic) {
							Pq pq=monthData.get(pqidc.getCode());
							pqs.add(pq);
						}
						dq.setPq(pqs);
						dqs.add(dq);
					}
					zq.setDq(dqs);
					monthZqList.add(zq);
				}
				result.put("monthData", monthZqList);
				result.put("dayData", dayZqList);
				result.put("success", true);
				Dd_Operation op=Dd_Operation.getInstance(userid, 16, "型号："+xh, "true", "");
				voMapper.addOperation(op);//添加登录日志到数据库
			}else {
				result.put("success", false);
				result.put("message", stu.getMessage());
				Dd_Operation op=Dd_Operation.getInstance(userid, 16, "型号："+xh, "false", stu.getMessage());
				voMapper.addOperation(op);//添加登录日志到数据库
			}
			logger.info("返回数据" + result);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("message", e.getMessage());
			Dd_Operation op=Dd_Operation.getInstance(userid, 16, "型号："+xh, "false", e.getMessage());
			voMapper.addOperation(op);//添加登录日志到数据库
			logger.error(e.getMessage(),e);
		}
		return result;
	}

	@RequestMapping(value = "/getquyuPermission")
	@ResponseBody
	public ServiceResult getquyu(@RequestParam Map para) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code_type", "XLQY");
		List<Dd_Info_Dic> dics = voMapper.getDic_Info(res);
		List<Dd_User_Quyu_Per> pers = voMapper.getPermission((String) para.get("userid"));//
		for (Dd_Info_Dic dic : dics) {
			dic.setChecked(false);
		}
		for (Dd_User_Quyu_Per per : pers) {
			for (Dd_Info_Dic dic : dics) {
				if ((per.getPermission()).equals(dic.getCode())) {
					dic.setChecked(true);
				}
			}
		}
		ServiceResult serviceResult = ServiceResult.success(dics);
		return serviceResult;
	}
	
	
}
