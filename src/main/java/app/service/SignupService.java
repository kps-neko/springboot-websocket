package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.form.SignupForm;
import app.mapper.UserInfoExMapper;
import app.mapper.UserInfoMapper;
import app.mapper.UserRolesMapper;
import app.model.UserInfo;
import app.model.UserRolesKey;

/**
 * Created by s-wada on 2015/11/25.
 */

@Service
public class SignupService {

    @Autowired
    private UserInfoExMapper userInfoExMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Transactional
    public boolean signup(SignupForm form) {
        UserInfo ui = userInfoExMapper.selectByUserId(form.getUserId());
        boolean result;
        if (ui!=null) {
            result = false;
        } else {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
            UserInfo insertUser = new UserInfo();
            insertUser.setEmail(form.getEmail());
            insertUser.setUserId(form.getUserId());
            insertUser.setEnabled(true);
            insertUser.setActivateFlag(false);
            insertUser.setOrganizationName("一般");
            insertUser.setPassword(encoder.encodePassword("test", "test"));
            userInfoMapper.insert(insertUser);

            UserRolesKey userRolesKey = new UserRolesKey();
            userRolesKey.setUserId(form.getUserId());
            userRolesKey.setRole("ROLE_USER");
            userRolesMapper.insert(userRolesKey);

            result = true;
        }
        return result;
    }
}
