package shop.mtcoding.bank.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.mtcoding.bank.domain.user.User;

@Getter
@RequiredArgsConstructor
public class LoginUser implements UserDetails {
    //Session에 내가넣은걸 만들면 객체 이름이 내맘대로라서 JPA가 찾아올 수 없기때문에 공통으로 만들기 위해서 사용
    //어댑터패턴과 같음

    private final User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>(); // 권한이 여러개일 수 있어서 Collection으로 씀
        authorities.add(() -> "ROLE_" + user.getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //구현하지않은 부분은 true로 걸어줘야 로그인이 가능하다
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
