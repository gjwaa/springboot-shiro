package com.gjw.config;

import com.gjw.bean.User;
import com.gjw.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
//        Arrays.asList(currentUser.getPerms().split(";")).forEach(perms -> info.addStringPermission(perms));
//        info.addStringPermission(currentUser.getPerms());
        String[] split = currentUser.getPerms().split(";");
        for (String perm : split) {
            info.addStringPermission(perm);
        }
        Set<String> stringPermissions = info.getStringPermissions();
        for (String stringPermission : stringPermissions) {
            System.err.println(stringPermission);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByAcc(userToken.getUsername());
        if (user == null) return null;
//        System.out.println(user);
        SecurityUtils.getSubject().getSession().setAttribute("loginUser",user.getAcc());

        return new SimpleAuthenticationInfo(user, user.getPwd(), ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
