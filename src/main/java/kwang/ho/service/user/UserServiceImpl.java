package kwang.ho.service.user;

import kwang.ho.dto.user.UserDto;
import kwang.ho.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserMapper userMapper;

    @Transactional
    @Override
    public void joinUser(UserDto userDto){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUser_Pw(passwordEncoder.encode(userDto.getUser_Pw()));
        userDto.setUser_Auth("USER");

        userMapper.saveUser(userDto);
    }
}
