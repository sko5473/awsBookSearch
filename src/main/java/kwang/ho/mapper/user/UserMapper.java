package kwang.ho.mapper.user;

import kwang.ho.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveUser(UserDto userDto);
}
