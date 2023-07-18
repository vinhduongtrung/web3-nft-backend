package vinh.entity;

import static vinh.entity.Permission.ARTIST_CREATE;
import static vinh.entity.Permission.ARTIST_DELETE;
import static vinh.entity.Permission.ARTIST_READ;
import static vinh.entity.Permission.ARTIST_UPDATE;
import static vinh.entity.Permission.USER_READ;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	USER(
			Set.of(
					USER_READ
				)
		),
	ARTIST(
			Set.of(
	                  ARTIST_READ,
	                  ARTIST_UPDATE,
	                  ARTIST_DELETE,
	                  ARTIST_CREATE,
	                  USER_READ
	          )
		);
	
	private Set<Permission> permissions;

	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	public List<SimpleGrantedAuthority> getAuthorities() {
	    var authorities = getPermissions()
	            .stream()
	            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	            .collect(Collectors.toList());
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	    return authorities;
	  }
}
