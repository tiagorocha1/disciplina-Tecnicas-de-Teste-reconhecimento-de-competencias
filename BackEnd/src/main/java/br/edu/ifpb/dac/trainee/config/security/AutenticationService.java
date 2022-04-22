package br.edu.ifpb.dac.trainee.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.repository.UserRepository;

@Service
public class AutenticationService implements UserDetailsService{

	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optional = usuarioRepository.findByEmail(username); 
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("Dados invalidos!!!");
	}

}	

