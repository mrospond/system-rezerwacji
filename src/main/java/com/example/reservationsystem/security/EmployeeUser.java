package com.example.reservationsystem.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeUser extends User {
    private String firstName;
    private String lastName;
    private String email;

    public static EmployeeUser fromUserDetails(UserDetails details) {
        return new EmployeeUser(details.getUsername(), details.getPassword(), details.isEnabled(),
                details.isAccountNonExpired(), details.isCredentialsNonExpired(), details.isAccountNonLocked(),
                details.getAuthorities());
    }

    public EmployeeUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
