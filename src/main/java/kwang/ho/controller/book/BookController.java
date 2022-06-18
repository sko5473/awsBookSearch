package kwang.ho.controller.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import kwang.ho.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @RequestMapping("/bookList.do")
    public String selectBookListWithPaging(PagingVO pagingVO, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage
            ) throws Exception {

        int total = bookService.selectBookTotalCount();
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
        model.addAttribute("list", bookService.selectBookListWithPaging(pagingVO));

        return "/bookList";
    }

    // 게시판 글쓰기페이지 이동
    @RequestMapping("/bookWrite.do")
    public String openBookWrite() throws Exception {
        return "/bookWrite";
    }

    // 게시판 작성
    @RequestMapping("/bookInsert.do")
    public String insertBook(BookDto bookDto, Principal principal) throws Exception {

        String id = principal.getName();
        bookDto.setCreator_Id(id);
        bookService.insertBook(bookDto);
        return "redirect:/bookList.do";
    }

    // 게시판 상세보기
    @RequestMapping("/bookDetail.do")
    public ModelAndView bookDetail(@RequestParam int bid) throws Exception {
        ModelAndView mv = new ModelAndView("/bookDetail");
        BookDto bookDto = bookService.selectBookDetail(bid);
        mv.addObject("bookDto", bookDto);
        return mv;
    }

    // 게시판 수정페이지 호출
    @RequestMapping("/openBookModify.do")
    public ModelAndView openBookModify(@RequestParam int bid) throws Exception {
        ModelAndView mv = new ModelAndView("/bookModify");
        BookDto bookDto = bookService.selectOpenBookModify(bid);
        mv.addObject("bookDto", bookDto);
        return mv;
    }

    // 게시판 수정
    @PreAuthorize("hasRole('ADMIN') or (#board.creator_Id==principal.username)")
    @RequestMapping("/bookModify.do")
    public String bookModify(BookDto bookDto, Principal principal) throws Exception {

        bookService.bookModify(bookDto);

        return "redirect:/bookList.do";
    }

    // 게시판 삭제
    @PreAuthorize("hasRole('ADMIN') or (#creator_Id==principal.username)")
    @RequestMapping("/bookDelete.do")
    public String bookDelete(@RequestParam int bid, @RequestParam("creator_Id") String creator_Id, Principal principal) throws Exception {

        bookService.bookDelete(bid);
        return "redirect:/bookList.do";
    }

}
