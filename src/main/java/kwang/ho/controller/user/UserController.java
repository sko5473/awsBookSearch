package kwang.ho.controller.user;

import kwang.ho.dto.user.UserDto;
import kwang.ho.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 폼
     * @return
     */

    @GetMapping("/join")
    public String joinForm() throws Exception{

        return "/join";
    }

    /**
     * 회원가입 진행
     * @param user
     * @return
     */

    @PostMapping("/join.do")
    public String join(UserDto userDto) throws Exception{
        userService.joinUser(userDto);

        return "redirect:/login";
    }
}
