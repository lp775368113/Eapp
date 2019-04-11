package com.controller;

import com.alibaba.fastjson.JSON;

import com.config.Constant;
import com.config.URLConstant;
import com.dao.VoMapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.entity.Dd_Operation;
import com.model.ProcessInstanceInputVO;
import com.util.AccessTokenUtil;
import com.util.LogFormatter;
import com.util.LogFormatter.LogEvent;
import com.util.ServiceResult;
import com.util.ServiceResultCode;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 企业 E应用审批解决方案示例代码
 * 实现了审批的基础功能
 */
@RestController
public class ProcessinstanceController {
	private static final Logger bizLogger = LoggerFactory.getLogger(ProcessinstanceController.class);

	@Resource
	private VoMapper voMapper;
	/**
	 * 欢迎页面
	 */
	@RequestMapping(value = "/Processinstance/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}


	/**
	 * 发起审批
	 */
	@RequestMapping(value = "/processinstance/start", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult<String> startProcessInstance(@RequestBody ProcessInstanceInputVO processInstance) {
		try {
			DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_START);
			OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
			request.setProcessCode(Constant.PROCESS_CODE);

			request.setFormComponentValues(processInstance.generateForms());

			/**
			 * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
			 * 本次quickstart，演示不传审批人的场景
			 */
			request.setApprovers("15523680118216167");
			request.setOriginatorUserId(processInstance.getOriginatorUserId());
			request.setDeptId(processInstance.getDeptId());
			request.setCcList("15523680118216167");
			request.setCcPosition("FINISH");
			String sqqx=processInstance.getTextForms().get(0).getValue();
			OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());

			if (response.getErrcode().longValue() != 0) {
				return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
			}
			Dd_Operation op=Dd_Operation.getInstance(processInstance.getOriginatorUserId(), 18, "申请区域："+sqqx, "true", "");
			voMapper.addOperation(op);//添加登录日志到数据库
			return ServiceResult.success(response.getProcessInstanceId());

		} catch (Exception e) {
			String errLog = LogFormatter.getKVLogData(LogEvent.END,
				LogFormatter.KeyValue.getNew("processInstance", JSON.toJSONString(processInstance)));
			bizLogger.info(errLog,e);
			Dd_Operation op=Dd_Operation.getInstance(processInstance.getOriginatorUserId(), 18, "", "false", e.getMessage());
			voMapper.addOperation(op);//添加登录日志到数据库
			return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
		}
	}

	/**
	 * 根据审批实例id获取审批详情
	 * @param instanceId
	 * @return
	 */
	@RequestMapping(value = "/pricessinstance/get", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult getProcessinstanceById(@RequestParam String instanceId) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_GET);
			OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
			request.setProcessInstanceId(instanceId);
			OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
			if (response.getErrcode().longValue() != 0) {
				return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
			}
			return ServiceResult.success(response.getProcessInstance());
		} catch (Exception e) {
			String errLog = LogFormatter.getKVLogData(LogEvent.END,
				LogFormatter.KeyValue.getNew("instanceId", instanceId));
			bizLogger.info(errLog,e);
			return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
		}
	}
}


