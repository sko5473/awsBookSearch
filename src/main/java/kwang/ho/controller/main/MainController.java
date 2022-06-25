package kwang.ho.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /**
     * localhost:8080 시 login 으로 redirect
     * @return
     */
    @GetMapping
    public String root() {
        return "/login";
    }

    @RequestMapping("/main")
    public String main() throws Exception {

        return "/index";
    }

}
