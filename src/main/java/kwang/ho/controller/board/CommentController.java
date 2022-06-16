package kwang.ho.controller.board;

import kwang.ho.dto.board.CommentDto;
import kwang.ho.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    // 게시판 댓글 목록
    @RequestMapping("/list")
    @ResponseBody
    private List<CommentDto> commentListService(int bid) throws Exception {

        return commentService.commentListService(bid);
    }

    // 게시판 댓글 작성
    @RequestMapping("/insert")
    @ResponseBody
    private int CommentServiceInsert(@RequestParam int bid, @RequestParam String content, Authentication authentication) throws Exception {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CommentDto commentDto = new CommentDto();
        commentDto.setBid(bid);
        commentDto.setContent(content);
        commentDto.setWriter(userDetails.getUsername());

        return commentService.commentInsertService(commentDto);
    }

    // 게시판 댓글 수정
    @RequestMapping("/update")
    @ResponseBody
    private int commentServiceUpdateProc(@RequestParam int cid, @RequestParam String content) throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setCid(cid);
        commentDto.setContent(content);

        return commentService.commentUpdateService(commentDto);
    }

    @RequestMapping("/delete/{cid}")
    @ResponseBody
    private int commentServiceDelete(@PathVariable int cid) throws  Exception {

        return commentService.commentDeleteService(cid);
    }
}
