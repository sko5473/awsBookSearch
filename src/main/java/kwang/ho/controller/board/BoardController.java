package kwang.ho.controller.board;

import kwang.ho.dto.board.AttachDTO;
import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.service.board.BoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        return "boardList";
    }

    // 게시판 글쓰기페이지 이동
    @RequestMapping("/boardWrite.do")
    public String openBoardWrite() throws Exception {
        return "boardWrite";
    }

    // 게시판 작성
    @RequestMapping("/boardInsert.do")
    public String insertBoard(BoardDto board, MultipartFile[] files, Principal principal) throws Exception {

        String id = principal.getName();
        board.setCreator_Id(id);
        board.setUpdater_Id(id);
        boardService.insertBoard(board, files);
        return "redirect:/boardList.do";
    }

    // 게시판 상세보기
    @RequestMapping("/boardDetail.do")
    public ModelAndView boardDetail(@RequestParam int bid,Model model) throws Exception {
        ModelAndView mv = new ModelAndView("boardDetail");
        BoardDto board = boardService.selectBoardDetail(bid);

        //첨부 상세보기
        List<AttachDTO> fileList = boardService.getAttachFileList(bid);
        model.addAttribute("fileList",fileList);

        mv.addObject("board", board);
        return mv;
    }

    // 게시판 수정페이지 호출
    @RequestMapping("/openBoardModify.do")
    public ModelAndView openBoardModify(@RequestParam int bid, Model model) throws Exception {

        ModelAndView mv = new ModelAndView("boardModify");
        BoardDto board = boardService.selectOpenBoardModify(bid);

        List<AttachDTO> fileList = boardService.getAttachFileList(bid);

        int fileListCount = boardService.selectFileListCount(bid);
        System.out.println("fileList = " + fileList);

        model.addAttribute("fileListCount", fileListCount);
        model.addAttribute("fileList", fileList);
        mv.addObject("board", board);

        return mv;
    }

    // 게시판 수정
    @PreAuthorize("hasRole('ADMIN') or (#board.creator_Id==principal.username)")
    @RequestMapping("/boardModify.do")
    public String boardModify(BoardDto board, MultipartFile[] files, Principal principal) throws Exception {

        String updater_Id = principal.getName();
        board.setUpdater_Id(updater_Id);

        boardService.boardModify(board, files);

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
        ModelAndView mv = new ModelAndView("boardReplyWrite");
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

    @GetMapping("/boardDownload.do")
    public void downloadAttachFile(@RequestParam(value="idx",required = false)final Integer idx, Model model,
                                   HttpServletResponse response) throws ParseException {
        if (idx == null) throw new RuntimeException("올바르지 않은 접근입니다.");

        AttachDTO fileInfo = boardService.getAttachDetail(idx);
        if (fileInfo == null || "Y".equals(fileInfo.getDelete_Time())){
            throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
        }

        String uploadDate = fileInfo.getInsert_Time().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadPath = Paths.get("/","home","ec2-user", "attach", uploadDate).toString();
        String filename = fileInfo.getOriginal_Name();
        File file = new File(uploadPath, fileInfo.getSave_Name());

        try{
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType("application/octet-stream");
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition","attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드에 실패하였습니다.");
        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }
    }
}