package kwang.ho.service.user;

import kwang.ho.dto.user.UserDto;
import kwang.ho.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public final UserMapper userMapper;

    @Transactional
    @Override
    public void joinUser(UserDto userDto){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUser_Pw(passwordEncoder.encode(userDto.getUser_Pw()));
        userDto.setUser_Auth("ROLE_USER");

        userMapper.saveUser(userDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userMapper.getUserAccount(username);
        if (userDto == null){
            throw new UsernameNotFoundException(username);
        }
        return userDto;
    }
}
