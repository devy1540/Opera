package com.opera.survway.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.opera.survway.admin.model.exception.ResearchException;
import com.opera.survway.admin.model.vo.PanelRewardHistory;
import com.opera.survway.admin.model.vo.ResearchTarget;
import com.opera.survway.admin.model.vo.SearchMember;
import com.opera.survway.admin.model.vo.TargetMember;
import com.opera.survway.common.model.vo.AllMember;
import com.opera.survway.common.model.vo.PageInfo;
import com.opera.survway.common.model.vo.ResearchState;
import com.opera.survway.common.model.vo.UploadFile;
import com.opera.survway.corporation.model.vo.Research;
import com.opera.survway.corporation.model.vo.ResearchChoice;
import com.opera.survway.corporation.model.vo.ResearchQuestion;

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

	int researchApproved(SqlSessionTemplate sqlSession, ResearchState researchState);

	int researchRefer(SqlSessionTemplate sqlSession, ResearchState researchState);

	int getListCountReferList(SqlSessionTemplate sqlSession);

	List<Map<String, String>> researchReferList(SqlSessionTemplate sqlSession, PageInfo pi);

	List<Map<String, Object>> researchReferDetail(SqlSessionTemplate sqlSession, int researchNo);

	int insertConferenceHistory(SqlSessionTemplate sqlSession, ResearchState researchState);

	int updateResearchPrice(SqlSessionTemplate sqlSession, ResearchState researchState);

	int getListResearchWaitingPayment(SqlSessionTemplate sqlSession);

	List<Map<String, String>> researchWaitingPayment(SqlSessionTemplate sqlSession, PageInfo pi);

	List<Map<String, Object>> researchWaitPaymentDetail(SqlSessionTemplate sqlSession, int researchNo);

	int insertReferConferenceHistory(SqlSessionTemplate sqlSession, ResearchState researchState);

	int getListCountPaymentCompletedList(SqlSessionTemplate sqlSession);

	List<Map<String, String>> paymentCompletedList(SqlSessionTemplate sqlSession, PageInfo pi);

	List<Map<String, String>> billsDetail(SqlSessionTemplate sqlSession, int billingHistoryNo);

	int getListCountSurveyReconstructionList(SqlSessionTemplate sqlSession);

	List<Map<String, String>> surveyReconstructionList(SqlSessionTemplate sqlSession, PageInfo pi);

	int deleteDiscriminationChoice(SqlSessionTemplate sqlSession, Research research);

	int deleteDiscriminationQuestion(SqlSessionTemplate sqlSession, Research research);

	int reconstruction(SqlSessionTemplate sqlSession, ResearchQuestion researchQuestion);

	int insertDiscriminationQuestion(SqlSessionTemplate sqlSession, ResearchQuestion researchQuestion);

	int insertDiscriminationChoice(SqlSessionTemplate sqlSession, ResearchChoice researchChoice);

	int researchReconstruction(SqlSessionTemplate sqlSession, ResearchState researchState);

	int updateResearchNamePanel(SqlSessionTemplate sqlSession, Research research);

	List<Map<String, Object>> discriminationDetail(SqlSessionTemplate sqlSession, int researchNo);

	int reconstructureRefer(SqlSessionTemplate sqlSession, ResearchState researchState);

	int reconstructureReferConferenceHistory(SqlSessionTemplate sqlSession, ResearchState researchState);

	ResearchTarget researchTargetMailing(SqlSessionTemplate sqlSession, int researchNo);

	List<TargetMember> getTargetList(SqlSessionTemplate sqlSession, ResearchTarget target, int researchEngagementGoals) throws ResearchException;

	int selectResearchEngagementGoals(SqlSessionTemplate sqlSession, int researchNo);

}
