package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import app.model.UserInfo;
import app.model.UserRolesKey;
import app.service.UserInfoService;
import app.service.UserRolesService;

import java.util.ArrayList;
import java.util.List;

/**
 * DBから認証情報を確認するためのサービス
 *
 * @author a-numadate
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
   @Autowired
   private UserInfoService userInfoService;
   @Autowired
   private UserRolesService userRolesService;

   /**
    * 独自処理に書き換え　DBから認証情報を取得する
    *
    */
   @Override
   public UserDetails loadUserByUsername(String username)
           throws UsernameNotFoundException {

       // ロールの情報も結合してとってくればSQL一本でできるかも
       UserInfo userInfo = userInfoService.getSearchUserIdUserInfo(username);
       if (userInfo == null) {
           throw new UsernameNotFoundException("");
       }

       List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();

       List<UserRolesKey> roles = userRolesService.getSearchUserIdUserRoles(username);
       for (UserRolesKey role: roles) {
           authorityList.add(new SimpleGrantedAuthority(role.getRole()));
       }
       return new MyUserDetails(userInfo, authorityList);
   }
}
