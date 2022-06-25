package kwang.ho.service.manage;

import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.user.UserDto;

import java.util.List;

public interface ManageService {

    List<UserDto> selectUserList() throws Exception;

    List<UserDto> selectAdminUserList() throws Exception;

    void updateAdminAuth(List<String> user_Id) throws Exception;

    void deleteAdminAuth(List<String> user_Id) throws Exception;

}
