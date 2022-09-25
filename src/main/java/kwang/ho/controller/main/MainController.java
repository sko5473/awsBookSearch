package kwang.ho.controller.main;

import kwang.ho.dto.book.BestSellerDto;
import kwang.ho.dto.book.BookDto;
import kwang.ho.service.main.MainService;
import kwang.ho.service.manage.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    public final MainService mainService;

    /**
     * localhost:8080 시 login 으로 redirect
     * @return
     */
    @GetMapping
    public String root() {
        return "login";
    }

    @RequestMapping("/main")
    public String main(Model model) throws Exception {

        model.addAttribute("best", mainService.selectBestList());

        return "index";
    }
}
