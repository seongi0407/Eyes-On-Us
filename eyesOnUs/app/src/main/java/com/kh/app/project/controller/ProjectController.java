package com.kh.app.project.controller;

import com.kh.app.project.service.ProjectService;
import com.kh.app.project.vo.ProjectManagerVo;
import com.kh.app.project.vo.ProjectRecordVo;
import com.kh.app.project.vo.ProjectVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("project")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService service;

    @GetMapping("projectMain")
    public String select(){
        return "project/projectMain";
    }
    @GetMapping("projectInsert")
    public String insert(){
        return "project/insertPrj";
    }

    @ResponseBody
    @PostMapping("projectInsert")
    public String insert(ProjectVo vo, HttpServletResponse resp, HttpServletRequest req){

        int result = service.insertProject(vo);

        if(result != 1){
            req.setAttribute("result","등록실패...");
        }
        req.setAttribute("result","등록 성공!");
        return "common/result";
    }

    @GetMapping("projectModify")
    public String modifyPrj(){
        return "project/modifyPrj";
    }

    @ResponseBody
    @PostMapping("projectModify")
    public String modifyPrj(ProjectVo vo, HttpServletRequest req){

        int result = service.modifyPrj(vo);

        if(result != 1){
            req.setAttribute("result","수정실패...");
        }
        req.setAttribute("result","수정 성공!");
        return "common/result";
    }

    @DeleteMapping("projectDelete")
    @ResponseBody
    public String deletePrj(String no,HttpServletRequest req){
        int result = service.deletePrj(no);

        if(result !=1){
            req.setAttribute("result","삭제실패");

        }
        req.setAttribute("result","삭제성공");
        return "common/result";
    }

    @GetMapping("listData")
    @ResponseBody
    public List<ProjectVo> projectListData(){
        List<ProjectVo> voList = service.projectList();
        return  voList;
    }
    @GetMapping("projectList")
    public String projectList(){
        return "project/projectList";
    }

    @GetMapping("detailData")
    @ResponseBody
    public ProjectVo getProjectByNo(ProjectVo vo){
        return service.getProjectByNo(vo.getNo());
    }

    @GetMapping("detail")
    public String projectDetail(){
        return "project/projectDetail";
    }

    @GetMapping("searchByName")
    @ResponseBody
    public List<ProjectVo>  projectSearchByName(ProjectVo vo){
       List<ProjectVo> voList = service.projectSearchByName(vo.getTitle());
       return voList;
    }






    /////////////////////////////////// PROJECT RECORD 컨트롤러////////////////////////////////////////////////////////////////////
    @GetMapping("record/insert")
    public String recordInsert(){
        return "prjConfRecord/recordInsert";
    }

    @PostMapping("record/insert")
    @ResponseBody
    public String recordInsert(ProjectRecordVo vo, HttpServletRequest req){

        int result = service.recordInsert(vo);

        if(result != 1){
            req.setAttribute("result","회의록 등록 실패..");
            return "common/result";
        }
        req.setAttribute("result","회의록 등록 완료!");
        return "common/result";
    }

    @ResponseBody
    @GetMapping("record/listData")
    public List<ProjectRecordVo> recordList(HttpServletRequest req){

        List<ProjectRecordVo> voList = service.recordList();


        return voList;
    }
    @GetMapping("record/list")
    public  String recordList() {
        return "prjConfRecord/recordList";
    }



    @GetMapping("record/detailData")
    @ResponseBody
    public ProjectRecordVo recordDetail(ProjectRecordVo vo){
        return service.recordDetail(vo.getNo());
    }

    @GetMapping("record/detail")
    public String recordDetail(){
        return "prjConfRecord/recordDetail";
    }

    @DeleteMapping("record/delete")
    @ResponseBody
    public int recordDetete(String no){
        return service.recordDelete(no);
    }

    ///////////////////////////////////////////// PROJECT_MANAGER //////////////////////////////////////////////////////////////////

    @GetMapping("manager/insert")
    public String managerInsert(){
        return "prjManager/managerInsert";
    }

    @PostMapping("manager/insert")
    @ResponseBody
    public int managerInsert(ProjectManagerVo vo){
        int result = service.managerInsert(vo);

        return result;

    }

    @GetMapping("manager/listData")
    @ResponseBody
    public List<ProjectManagerVo> managerListData(){
        List<ProjectManagerVo> voList = service.managerList();
        return  voList;
    }

    @GetMapping("manager/list")
    public String managerList(){
        return "prjManager/managerList";
    }

    @DeleteMapping("manager/delete")
    @ResponseBody
    public int managerDelete(String no){
        int result = service.managerDelete(no);
        return result;
    }


}
