package com.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.entity.Dd_Dept;
import com.entity.Dd_Info_Dic;
import com.entity.Dd_Operation;
import com.entity.Dd_User;
import com.entity.Dd_User_Quyu_Per;
@Mapper
public interface VoMapper{

	Dd_User getUser(String userid);
	
	List<Dd_Info_Dic> getQuyu(String quyuname);

	void putUser(Map para);

	void insertdept(Dd_Dept d);

	List<Dd_User_Quyu_Per> getPermission(String userid);
	
	List<Dd_User_Quyu_Per> getAllPermission(String userid);

	List<Dd_Info_Dic> getDic_Info(Map res);

	void addDd_User_Quyu_Per(Dd_User_Quyu_Per p);

	void updateDd_User_Quyu_Per(Dd_User_Quyu_Per old);

	void addOperation(Dd_Operation op);

	int countOperation(String userid);
	
	void updateUser(Map res);
}