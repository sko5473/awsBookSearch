package kwang.ho.controller.book;

import kwang.ho.dto.board.AttachDTO;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookAttachDto;
import kwang.ho.dto.book.BookDto;
import kwang.ho.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //도서정보 리스트
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

    // 도서정보 등록 페이지 이동
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookWrite.do")
    public String openBookWrite() throws Exception {

        return "/bookWrite";
    }

    // 도서정보 등록
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookInsert.do")
    public String insertBook(BookDto bookDto, MultipartFile[] files, Principal principal) throws Exception {

        String id = principal.getName();
        bookDto.setCreator_Id(id);
        bookService.insertBook(bookDto, files);
        return "redirect:/bookList.do";
    }

    // 도서정보 상세보기
    @RequestMapping("/bookDetail.do")
    public ModelAndView bookDetail(@RequestParam int bid, Model model) throws Exception {
        ModelAndView mv = new ModelAndView("/bookDetail");
        bookService.selectBestDetail(bid);
        BookDto bookDto = bookService.selectBookDetail(bid);
        System.out.println(bid);
        List<AttachDTO> fileList = bookService.getAttachFileList(bid);
        model.addAttribute("fileList", fileList);

        mv.addObject("bookDto", bookDto);
        return mv;
    }

    // 도서정보 수정페이지 호출
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/openBookModify.do")
    public ModelAndView openBookModify(@RequestParam int bid,Model model) throws Exception {
        ModelAndView mv = new ModelAndView("/bookModify");
        BookDto bookDto = bookService.selectOpenBookModify(bid);

        List<AttachDTO> fileList = bookService.getAttachFileList(bid);

        model.addAttribute("fileList", fileList);
        mv.addObject("bookDto", bookDto);
        return mv;
    }

    // 도서정보 수정
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookModify.do")
    public String bookModify(BookDto bookDto, MultipartFile[] files, Principal principal) throws Exception {

        bookService.bookModify(bookDto, files);

        return "redirect:/bookList.do";
    }

    // 도서정보 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookDelete.do")
    public String bookDelete(@RequestParam int bid, @RequestParam("creator_Id") String creator_Id, Principal principal) throws Exception {

        bookService.bookDelete(bid);
        return "redirect:/bookList.do";
    }

    // 메인페이지 베스트셀러 상세보기
    @RequestMapping("/bestSellerDetail")
    @ResponseBody
    public BookAttachDto bestSellerDetail(int bid) throws Exception {

        return bookService.selectBestDetail(bid);
    }
}
