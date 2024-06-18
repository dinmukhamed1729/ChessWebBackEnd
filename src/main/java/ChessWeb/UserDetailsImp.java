package ChessWeb;

import ChessWeb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;		

public class UserDetailsImp implements UserDetails {
    
    private User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.role().stream()
        .map(it -> new GrantedAuthorityImpl(it.name() ))
        .toList();
    }

    @Override
    public String getPassword() {
        return user.password(); 
    }

    @Override
    public String getUsername() {
        return user.name();
    }
}