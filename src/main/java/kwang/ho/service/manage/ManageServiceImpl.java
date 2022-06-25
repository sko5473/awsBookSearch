package kwang.ho.service.manage;

import kwang.ho.dto.user.UserDto;
import kwang.ho.mapper.manage.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public List<UserDto> selectUserList() throws Exception {

        return manageMapper.selectUserList();
    }

    @Override
    public List<UserDto> selectAdminUserList() throws Exception {

        return manageMapper.selectAdminUserList();
    }

    @Override
    public void updateAdminAuth(List<String> user_Id) throws Exception {

        manageMapper.updateAdminAuth(user_Id);
    }

    @Override
    public void deleteAdminAuth(List<String> user_Id) throws Exception {

        manageMapper.deleteAdminAuth(user_Id);
    }
}
