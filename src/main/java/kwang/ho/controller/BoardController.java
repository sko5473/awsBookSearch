package kwang.ho.controller;

import kwang.ho.board.BoardDto;
import kwang.ho.board.CommentDto;
import kwang.ho.board.PagingVO;
import kwang.ho.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService; //서비스와 연결

    // 게시판 목록
    @RequestMapping("/boardList.do")
    public String selectBoardListWithPaging(PagingVO pagingVO, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage, BoardDto boardDto) throws Exception {

        int total = boardService.selectBoardTotalCount();
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "5";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "5";
        }
        pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
        model.addAttribute("paging", pagingVO);
        model.addAttribute("list", boardService.selectBoardListWithPaging(pagingVO));

        return "/boardList";
    }

    // 게시판 글쓰기페이지 이동
    @RequestMapping("/boardWrite.do")
    public String openBoardWrite() throws Exception {
        return "/boardWrite";
    }

    // 게시판 작성
    @RequestMapping("/boardInsert.do")
    public String insertBoard(@ModelAttribute BoardDto board) throws Exception {
        boardService.insertBoard(board);
        return "redirect:/boardList.do";
    }

    // 게시판 상세보기
    @RequestMapping("/boardDetail.do")
    public ModelAndView boardDetail(@RequestParam int bid) throws Exception {
        ModelAndView mv = new ModelAndView("/boardDetail");
        BoardDto board = boardService.selectBoardDetail(bid);
        mv.addObject("board", board);
        return mv;
    }

    // 게시판 수정페이지 호출
    @RequestMapping("/openBoardModify.do")
    public ModelAndView openBoardModify(@RequestParam int bid) throws Exception {
        ModelAndView mv = new ModelAndView("/boardModify");
        BoardDto board = boardService.selectOpenBoardModify(bid);
        mv.addObject("board", board);
        return mv;
    }

    // 게시판 수정
    @RequestMapping("/boardModify.do")
    public String boardModify(BoardDto board) throws Exception {
        boardService.boardModify(board);
        return "redirect:/boardList.do";
    }

    // 게시판 삭제
    @RequestMapping("/boardDelete.do")
    public String boardDelete(@RequestParam int bid) throws Exception {
        boardService.boardDelete(bid);
        return "redirect:/boardList.do";
    }

    // 게시판 답글쓰기 페이지 호출
    @RequestMapping("/boardReplyWrite.do")
    public ModelAndView boardReplyWrite(@ModelAttribute BoardDto board) throws Exception {
        ModelAndView mv = new ModelAndView("/boardReplyWrite");
        mv.addObject("board", board);
        return mv;
    }

    // 게시판 답글 저장
    @RequestMapping("/boardReply.do")
    public String boardReply(BoardDto board) throws Exception {
        boardService.boardReply(board);

        return "redirect:/boardList.do";
    }
}