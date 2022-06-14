package kwang.ho.mapper.user;

import kwang.ho.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 로그인
    UserDto getUserAccount(String userId);

    // 회원가입
    void saveUser(UserDto userDto);
}
