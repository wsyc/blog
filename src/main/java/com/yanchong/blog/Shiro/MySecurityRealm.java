package com.yanchong.blog.Shiro;

import com.yanchong.blog.Entity.User;
import com.yanchong.blog.Entity.UserVo;
import com.yanchong.blog.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MySecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentUserName = (String)principalCollection.getPrimaryPrincipal();
//        List<String> roles = new ArrayList<String>();  //角色
//        List<String> prems = new ArrayList<String>(); //权限
//        roles.add("baidu");
//        roles.add("google");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addRoles(roles);
//        authorizationInfo.addStringPermissions(prems);
        return authorizationInfo;
    }
    /**
     * 认证，验证当前登录的Subject
     * LoginController.login 方法中调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        SecurityUtils.getSubject().getSession().getId().AuthenticationInfo.class;
        String userName = (String)authenticationToken.getPrincipal();
        //为了测试通过，这里暂时写死， user： admin password： 123456
        // UserVo user = loginClient.selectUserByName(userName);
        User user = userService.getUserByUsername(userName);
        if(user == null){
            throw new UnknownAccountException();//没找到帐号
        }
        UserVo userVo = new UserVo();
        userVo.setName(userName);
        userVo.setPassword(new Md5Hash(user.getPassword()).toHex()); //与SecurityManager加密方式一致，使用一次散列，并转为16进制，验证方能通过。
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
