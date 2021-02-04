package com.mortegagarcia.gradebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private String userName;
	private String email;
	private String password;
	private Boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.userName = user.getUsername();
		this.email = user.getEmail().getEmail();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.authorities = user.getRoles().stream()
				.map(String::valueOf)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public String getEmail() {
		return email;
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
