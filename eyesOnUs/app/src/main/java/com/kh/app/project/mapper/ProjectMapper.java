package com.kh.app.project.mapper;

import com.kh.app.project.vo.ProjectManagerVo;
import com.kh.app.project.vo.ProjectRecordVo;
import com.kh.app.project.vo.ProjectVo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Insert("INSERT INTO PROJECT(NO,TITLE,CODE,EMP_NO,STATE_A_NO,CONTENT) VALUES(SEQ_PROJECT.NEXTVAL,#{title},#{code},#{empNo},#{stateANo},#{content})")
    int insertProject(ProjectVo vo);

    @Update("UPDATE PROJECT SET CONTENT = #{content},STATE_A_NO = #{stateANo}, MODIFY_DATE = CURRENT_TIMESTAMP WHERE NO = #{no}")
    int modifyPrj(ProjectVo vo);

    @Select("SELECT P.NO as NO,P.CODE as CODE, P.EMP_NO AS EMPNO, P.STATE_A_NO as STATEANO,\n" +
            "TO_CHAR(P.START_DATE,'YYYY.MM.DD') as STARTDATE, TO_CHAR(P.END_DATE,'YYYY.MM.DD')as ENDDATE,TO_CHAR(P.MODIFY_DATE,'YYYY.MM.DD') as MODIFYDATE,\n" +
            "P.title as title,E.NAME as PM, D.NAME as DEPT,S.CONTENT as STATE, P.CONTENT AS CONTENT\n" +
            "FROM PROJECT P\n" +
            "JOIN STATE_A S\n" +
            "ON P.STATE_A_NO = S.NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON P.EMP_NO = E.NO\n" +
            "JOIN DEPARTMENT D\n" +
            "ON P.CODE = D.CODE\n" +
            "ORDER BY P.NO ASC")
            List<ProjectVo> projectList();


    @Delete("DELETE PROJECT WHERE NO = #{no}")
    int deletePrj(String no);

    @Select("SELECT P.NO as NO,P.title as title, E.NAME as PM, D.NAME as DEPT,S.CONTENT as STATE, P.CONTENT AS CONTENT \n" +
            ",TO_CHAR(P.MODIFY_DATE,'YYYY.MM.DD')  as modifyDate , P.START_DATE as startDate\n" +
            "FROM PROJECT P\n" +
            "JOIN STATE_A S\n" +
            "ON P.STATE_A_NO = S.NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON P.EMP_NO = E.NO\n" +
            "JOIN DEPARTMENT D\n" +
            "ON P.CODE = D.CODE\n" +
            "WHERE P.NO = #{no}")
    ProjectVo getProjectByNo(String no);

    @Select("SELECT P.NO as NO,P.title as title, E.NAME as PM, D.NAME as DEPT,S.CONTENT as STATE, P.CONTENT AS CONTENT \n" +
            ",TO_CHAR(P.MODIFY_DATE,'YYYY.MM.DD')  as modifyDate , P.START_DATE as startDate\n" +
            "FROM PROJECT P\n" +
            "JOIN STATE_A S\n" +
            "ON P.STATE_A_NO = S.NO\n" +
            "JOIN EMPLOYEE E\n" +
            "ON P.EMP_NO = E.NO\n" +
            "JOIN DEPARTMENT D\n" +
            "ON P.CODE = D.CODE\n" +
            "WHERE P.TITLE LIKE '%' || #{title} || '%'")
    List<ProjectVo> projectSearchByName(String title);
    ////////////////////////PROJECT RECORD CRUD ///////////////////////////////////////

    @Insert("INSERT INTO CONFERENCE_RECORD(NO,PRJ_NO,TITLE,CONTENT) VALUES(SEQ_CONFERENCE_RECORD.NEXTVAL,#{prjNo},#{title},#{content})")
    int recordInsert(ProjectRecordVo vo);

    @Select("SELECT * FROM CONFERENCE_RECORD WHERE DEL_YN ='N' ORDER BY PRJ_NO ASC")
    List<ProjectRecordVo> recordList();

    @Select("SELECT C.NO as no,C.PRJ_NO as prjNo , M.EMP_NO as empNo, E.NAME as name, C.TITLE as title, C.CONTENT as content, C.ENL_DATE as enlDate\n" +
            "FROM CONFERENCE_RECORD C\n" +
            "LEFT JOIN PROJECT P\n" +
            "ON C.PRJ_NO = P.NO\n" +
            "LEFT JOIN PROJECT_MANAGER M\n" +
            "ON C.PRJ_NO = M.PRO_NO\n" +
            "LEFT JOIN EMPLOYEE E\n" +
            "ON E.NO = M.EMP_NO\n"+
            "WHERE C.NO = #{no}")
    ProjectRecordVo recordDetail(String no);

///////////////////////////////////// PROJECT_MANAGER CRUD ///////////////////////////////////////////////////////////

    @Insert("INSERT INTO PROJECT_MANAGER(NO,PRO_NO,EMP_NO) VALUES(SEQ_PROJECT_MANAGER.NEXTVAL,#{proNo},#{empNo})")
    int managerInsert(ProjectManagerVo vo);


    @Select("SELECT M.NO as NO, M.PRO_NO as PRONO, M.EMP_NO as EMPNO, E.NAME as NAME  FROM\n" +
            "PROJECT_MANAGER M\n" +
            "JOIN EMPLOYEE E\n" +
            "ON M.EMP_NO = E.NO\n" +
    "ORDER BY M.PRO_NO ASC")
    List<ProjectManagerVo> managerList();

    @Delete("DELETE PROJECT_MANAGER WHERE NO = #{no}")
    int managerDelete(String no);

}
