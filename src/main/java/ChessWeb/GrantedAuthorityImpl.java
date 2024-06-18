package ChessWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {

    String authority;
    
    @Override
    public String getAuthority() {
        return authority;
    }
}