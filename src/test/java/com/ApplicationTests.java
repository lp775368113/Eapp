package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.text.vo.daochu;
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
		//生成权限数据
//		Map<String, Object> resultMap = new HashMap<>();
//		String userid="040201462726258055";
//		//String username="刘鹏";
//		String quyu="深圳2区";
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
//	List<daochu> list = voMapper.getAlldaochu();
//		for (daochu d : list) {
//			if (d.getC() != "" && d.getC() != null) {
//				d.setC(getZW(d.getC()));
//			}
//			if (d.getA() != "" && d.getA() != null) {
//				d.setA(getZW(d.getA()));
//			}
//			if (d.getB() != "" && d.getB() != null) {
//				d.setB(getZW(d.getB()));
//			}
//			if (d.getD() != "" && d.getD() != null) {
//				d.setD(getZW(d.getD()));
//			}
//			if (d.getE() != "" && d.getE() != null) {
//				d.setE(getZW(d.getE()));
//			}
//			if (d.getG() != "" && d.getG() != null) {
//				d.setG(getZW(d.getG()));
//			}
//			if (d.getH() != "" && d.getH() != null) {
//				d.setH(getZW(d.getH()));
//			}
//			voMapper.updatedaochu(d);
//		}
//		List<daochu> list = voMapper.getAlldaochu();
//		for (daochu d : list) {
//			try {
// 			String str=d.getD();
// 			int i;
// 			if(str.indexOf("(", 1)==-1) {
// 				if(str.indexOf("（",1)==-1) {
// 					i=str.indexOf(" ",1);
// 				}else {
// 					i=str.indexOf("（",1);
// 				}
// 			}else {
// 				i=str.indexOf("(", 1);
// 			}
// 			String hou=str.substring(str.indexOf("阻")+1, str.indexOf("-",1));
// 			String qian=str.substring(str.indexOf("=", 1)+1, i);
// 			String out=qian+"/"+hou;
// 			d.setD(out);
//			} catch (Exception e) {
//				continue;
//			}
//			String bb=d.getB();
//			if("SMD0603".equals(bb)) {
//				d.setB("R0603");
//				voMapper.updatedaochu(d);
//			}
//			if("SMD0805".equals(bb)) {
//				d.setB("R0805");
//				voMapper.updatedaochu(d);
//			}
			
//		}
		JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
		jwpfb.setServiceClass(CommonService.class);
		InputStream is = IndexController.class.getClassLoader().getResourceAsStream("para.properties");
		Properties pro = new Properties();
		pro.load(is);
		String webServiceURL = pro.getProperty("webServiceURL");
		jwpfb.setAddress(webServiceURL);
		CommonService hw = (CommonService) jwpfb.create();
		String res = "{endTime:\"\",startTime:\"\",xh:\"\"}";
		String json = hw.getQueryData(res);
		JSONObject jsonObject = JSONObject.fromObject(json);
		System.out.println(jsonObject);
 	}

	public String getZW(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO8859-1"), "gbk");
	}

}
