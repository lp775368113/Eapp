package com;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.config.Constant;
import com.config.URLConstant;
import com.controller.IndexController;
import com.dao.VoMapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.entity.Dd_Info_Dic;
import com.entity.Dd_User;
import com.entity.Dd_User_Quyu_Per;
import com.entity.Model;
import com.quyu.Pq;
import com.servicecode.CommonService;
import com.taobao.api.ApiException;
import com.util.AccessTokenUtil;
import com.util.ServiceResult;
import com.util.Tool;

import net.sf.json.JSONObject;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Resource
	private VoMapper voMapper;
	
	@Test
	public void test() throws IOException, ApiException, RuntimeException {
//		Map<String, Object> resultMap = new HashMap<>();
//		String userid="032539495029396602";
////		String username="刘鹏";
//		String quyu="苏州区,无锡区,沪昆区,杭州区,宁波区,绍兴区,温台区,大客户区,华北区";
////		resultMap.put("name", username);
////		resultMap.put("userid", userid);
////		resultMap.put("removed", "0");
////		voMapper.putUser(resultMap);
//		List<Dd_Info_Dic> quyulist=voMapper.getQuyu(quyu);//用户提交的区域权限数据
//		for(Dd_Info_Dic newPer:quyulist) {
//			System.out.println(newPer);
//			Dd_User_Quyu_Per p=new Dd_User_Quyu_Per();
//			p.setType("1");
//			p.setUserid(userid);
//			p.setRemoved("0");
//			p.setPermission(newPer.getCode());
//			voMapper.addDd_User_Quyu_Per(p);
//		}
//		System.out.println("==========数量："+quyulist.size());
//		
	}
	

}
