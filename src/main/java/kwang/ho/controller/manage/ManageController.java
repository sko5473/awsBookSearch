package kwang.ho.controller.manage;

import kwang.ho.dto.book.BookDto;
import kwang.ho.service.manage.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {

    public final ManageService manageService;

    @RequestMapping("/manage")
    public String manage() throws Exception {

        return "manage/manage";
    }

    // 관리자 권한 페이지 이동
    @RequestMapping("/userAuth")
    public String userAuthselect(Model model) throws Exception{

        model.addAttribute("user", manageService.selectAdminUserList());

        return "manage/manageAuthUser";
    }

    // 관리자 권한 추가 페이지 이동
    @RequestMapping("/manageAuthUserSelect")
    public String manageAuthUserSelect(Model model) throws Exception{

        model.addAttribute("user", manageService.selectUserList());

        return "manage/manageAuthUserSelect";
    }

    // 체크된 유저 관리자 권한 부여
    @ResponseBody
    @RequestMapping("/checkedUserUpdateAdminAuth")
    public boolean checkedUserUpdateAdminAuth(@RequestBody List<String> user_Id) throws Exception{

        manageService.updateAdminAuth(user_Id);

        return true;
    }

    // 관리자 권한 해제
    @ResponseBody
    @RequestMapping("/deleteAdminAuth")
    public boolean deleteAdminAuth(@RequestBody List<String> user_Id) throws Exception{

        manageService.deleteAdminAuth(user_Id);

        return true;
    }

    // 베스트셀러 선택 페이지 이동
    @RequestMapping("/bestSeller")
    public String bestSeller() throws Exception{

        return "manage/manageBestSeller";
    }

    // 베스트셀러 목록 저장
    @ResponseBody
    @RequestMapping("/bestOptionSave")
    public boolean bestOptionSave(@RequestBody List<String> bid) throws Exception{

        manageService.saveBestList(bid);

        return true;
    }

    // 베스트셀러상세페이지에서 검색
    @PostMapping(value = "/searchBest")
    @ResponseBody
    public List<BookDto> searchBest(@RequestBody String bestKeyword) throws Exception {
        List<BookDto> bookDto = manageService.searchBest(bestKeyword);

        return bookDto;
    }

}
