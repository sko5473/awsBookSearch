package kwang.ho.controller.manage;

import kwang.ho.service.manage.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {

    public final ManageService manageService;


    @RequestMapping("/manage")
    public String manage() throws Exception {

        return "/manage/manage";
    }

    // 관리자 권한 페이지 이동
    @RequestMapping("/userAuth")
    public String userAuthselect(Model model) throws Exception{

        model.addAttribute("user", manageService.selectAdminUserList());

        return "/manage/manageAuthUser";
    }

    // 관리자 권한 추가 페이지 이동
    @RequestMapping("/manageAuthUserSelect")
    public String manageAuthUserSelect(Model model) throws Exception{

        model.addAttribute("user", manageService.selectUserList());

        return "/manage/manageAuthUserSelect";
    }

    // 체크된 유저 관리자 권한 부여
    @ResponseBody
    @RequestMapping("/checkedUserUpdateAdminAuth")
    public boolean checkedUserUpdateAdminAuth(@RequestBody List<String> user_Id) throws Exception{

        manageService.updateAdminAuth(user_Id);

        return true;
    }

    @ResponseBody
    @RequestMapping("/deleteAdminAuth")
    public boolean deleteAdminAuth(@RequestBody List<String> user_Id) throws Exception{

        manageService.deleteAdminAuth(user_Id);

        return true;
    }
}
