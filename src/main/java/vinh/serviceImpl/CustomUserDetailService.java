package vinh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vinh.entity.Artist;
import vinh.entity.User;
import vinh.repository.ArtistRepository;
import vinh.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		}
		
		Artist artist = artistRepository.findByEmail(email);
		
		if(artist != null) {
			return new org.springframework.security.core.userdetails.User(artist.getEmail(), artist.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("cannot find user with email :" + email);
	}

}
