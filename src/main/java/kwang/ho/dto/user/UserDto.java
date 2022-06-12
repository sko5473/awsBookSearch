package kwang.ho.dto.user;

import lombok.Data;

@Data
public class UserDto {

    private int user_No;
    private String user_Id;
    private String user_Pw;
    private String user_Name;
    private String user_Auth;
    private String create_Date;
    private String update_Date;
}
