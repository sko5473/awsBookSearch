package kwang.ho.dto.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserDto implements UserDetails {

    private int user_No;
    private String user_Id;
    private String user_Pw;
    private String user_Name;
    private String user_Auth;
    private String create_Date;
    private String update_Date;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(user_Auth));
        return authList;
    }

    @Override
    public String getPassword() {
        return user_Pw;
    }

    @Override
    public String getUsername() {
        return user_Id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
