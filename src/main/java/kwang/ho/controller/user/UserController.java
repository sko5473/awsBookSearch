package kwang.ho.controller.user;

import kwang.ho.dto.user.UserDto;
import kwang.ho.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/login";
    }

    /**
     * 회원가입 폼
     * @return
     */

    @GetMapping("/join")
    public String joinForm() throws Exception{

        return "/join";
    }

    /**
     * 로그인 폼
     * @return
     */
    @GetMapping("/login_deny")
    public String login_deny(){
        return "/login_deny";
    }

    /**
     * 회원가입 진행
     * @param userDto
     * @return
     */

    @PostMapping("/join")
    public String join(UserDto userDto) throws Exception{
        userService.joinUser(userDto);

        return "/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }


}
