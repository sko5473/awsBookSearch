package kwang.ho.controller.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import kwang.ho.service.book.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 도서검색 목록보기
    @RequestMapping("/searchList.do")
    public String selectSearchListWithPaging(PagingVO pagingVO, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage
            , @RequestParam(value="keyword") String keyword
            , @RequestParam(value="searchType") String searchType
    ) throws Exception {

        int total = searchService.selectSearchTotalCount(pagingVO);
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }
        pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), keyword, searchType);
        model.addAttribute("paging", pagingVO);
        model.addAttribute("list", searchService.selectSearchListWithPaging(pagingVO));

        return "/searchList";
    }

    // 도서검색 상세보기
    @RequestMapping("/searchDetail.do")
    public ModelAndView searchDetail(@RequestParam int bid) throws Exception {
        ModelAndView mv = new ModelAndView("/searchDetail");
        BookDto bookDto = searchService.selectSearchDetail(bid);
        mv.addObject("bookDto", bookDto);
        return mv;
    }
}
