package com.opera.survway.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import com.opera.survway.admin.model.vo.PanelRewardHistory;
import com.opera.survway.admin.model.vo.SearchMember;
import com.opera.survway.common.model.vo.AllMember;
import com.opera.survway.common.model.vo.PageInfo;
import com.opera.survway.common.model.vo.UploadFile;

public interface AdminDao {

	List<AllMember> memberInfoManagement(SqlSessionTemplate sqlSession, SearchMember searchMember);

	int getListCountPanel(SqlSessionTemplate sqlSession, SearchMember searchMember);

	AllMember selectMember(SqlSessionTemplate sqlSession, int mno);

	int getListCountPanelCashoutApplicant(SqlSessionTemplate sqlSession);

	List<PanelRewardHistory> panelCashoutApplication(SqlSessionTemplate sqlSession, PageInfo pi);

	int cashoutPeople(SqlSessionTemplate sqlSession, List<String> cnoArr);

	int getListCountManageCashoutComplete(SqlSessionTemplate sqlSession);

	List<PanelRewardHistory> manageCashoutComplete(SqlSessionTemplate sqlSession, PageInfo pi);

	int getListCountNewPanel(SqlSessionTemplate sqlSession, SearchMember searchMember);

	List<AllMember> getListNewPanel(SqlSessionTemplate sqlSession, SearchMember searchMember);

	AllMember getNewPanelDetail(SqlSessionTemplate sqlSession, int mno);

	int getListCountArrovalList(SqlSessionTemplate sqlSession);

	List<Map<String, String>> researchApprovalWaitList(SqlSessionTemplate sqlSession, PageInfo pi);

	List<Map<String, Object>> researchApprovalDetail(SqlSessionTemplate sqlSession, int researchNo);

	UploadFile questionImage(SqlSessionTemplate sqlSession, int questionNo);

	UploadFile choiceImage(SqlSessionTemplate sqlSession, int choiceNo);

}
