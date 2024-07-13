package com.kh.app.board.service;

import com.kh.app.board.dao.NoticeDao;
import com.kh.app.board.vo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeDao dao;

    // 작성하기
    public int write(NoticeVo vo) {
        return dao.write(vo);
    }

    // 목록
    public List<NoticeVo> listData() {
        return dao.listData();
    }

}
