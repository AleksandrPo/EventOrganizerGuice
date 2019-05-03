package edu.eventorganizer.auth.realm;

import com.google.inject.Inject;
import edu.eventorganizer.auth.authentication.MySaltedAuthenticationInfo;
import edu.eventorganizer.auth.dao.UserDao;
import edu.eventorganizer.auth.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Inject
    UserDao userDao;

    @Override
    protected AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();
        if(username == null) {
            System.out.println("USERNAME IS NULL");
            return null;
        }

        final User user = userDao.findByUsername(username);
        if(user == null) {
            System.out.println("No account found");
            return null;
        }

        SaltedAuthenticationInfo info = new MySaltedAuthenticationInfo(username, user.getPassword(), user.getSalt());

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = null;
        roles.add(userDao.findRole(username));
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }
}
