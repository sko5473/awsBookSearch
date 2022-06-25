package kwang.ho.controller.board;

import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService; //서비스와 연결

    // 게시판 목록
    @RequestMapping("/boardList.do")
    public String selectBoardListWithPaging(PagingVO pagingVO, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {

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
    public String insertBoard(BoardDto board, Principal principal) throws Exception {

        String id = principal.getName();
        board.setCreator_Id(id);
        board.setUpdater_Id(id);
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
    @PreAuthorize("hasRole('ADMIN') or (#board.creator_Id==principal.username)")
    @RequestMapping("/boardModify.do")
    public String boardModify(BoardDto board, Principal principal) throws Exception {

        String updater_Id = principal.getName();
        board.setUpdater_Id(updater_Id);
        boardService.boardModify(board);

        return "redirect:/boardList.do";
    }

    // 게시판 삭제
    @PreAuthorize("hasRole('ADMIN') or (#creator_Id==principal.username)")
    @RequestMapping("/boardDelete.do")
    public String boardDelete(@RequestParam int bid, @RequestParam("creator_Id") String creator_Id, Principal principal) throws Exception {

        boardService.boardDelete(bid);
        return "redirect:/boardList.do";
    }

    // 게시판 답글쓰기 페이지 호출
    @RequestMapping("/boardReplyWrite.do")
    public ModelAndView boardReplyWrite(BoardDto board) throws Exception {
        ModelAndView mv = new ModelAndView("/boardReplyWrite");
        mv.addObject("board", board);
        return mv;
    }

    // 게시판 답글 저장
    @RequestMapping("/boardReply.do")
    public String boardReply(BoardDto board, Principal principal) throws Exception {

        String id = principal.getName();
        board.setCreator_Id(id);
        board.setUpdater_Id(id);
        boardService.boardReply(board);

        return "redirect:/boardList.do";
    }
}