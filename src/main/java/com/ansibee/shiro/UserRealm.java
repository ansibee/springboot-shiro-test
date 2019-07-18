package com.ansibee.shiro;


import com.ansibee.domain.UserInfo;
import com.ansibee.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRealm extends AuthorizingRealm{

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("【自定义Realm】用户认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserInfo dbUser = userService.findByName(token.getUsername());
        if (dbUser == null){
            return null;
        }

        return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),"");
    }

    /**
     * 用于授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【自定义Realm】用户授权");
        SimpleAuthorizationInfo authorizationInfo  = new SimpleAuthorizationInfo();
        Subject subject =  SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        UserInfo dbUser = userService.findById(userInfo.getUserId());
        authorizationInfo.addStringPermission(dbUser.getPerms());
        return authorizationInfo;
    }
}
