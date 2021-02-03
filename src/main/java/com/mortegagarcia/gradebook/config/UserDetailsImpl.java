package com.mortegagarcia.gradebook.config;

import com.mortegagarcia.gradebook.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private String userName;
	private String password;
	private Boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.userName = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.authorities = user.getRoles().stream()
				.map(String::valueOf)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
//		TODO: Remove hardcoding in isAccountNonExpired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
//		TODO: Remove hardcoding in isAccountNonLocked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
//		TODO: Remove hardcoding in isCredentialsNonExpired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
