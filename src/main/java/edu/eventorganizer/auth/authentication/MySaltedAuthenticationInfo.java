package edu.eventorganizer.auth.authentication;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class MySaltedAuthenticationInfo implements SaltedAuthenticationInfo {
    private static final long serialVersionUID = -3657585627728L;

    private final String username;
    private final String password;
    private final String salt;

    public MySaltedAuthenticationInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public ByteSource getCredentialsSalt() {
        return new SimpleByteSource(Base64.decode(salt));
    }

    @Override
    public PrincipalCollection getPrincipals() {
        return new SimplePrincipalCollection(username, password);
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
