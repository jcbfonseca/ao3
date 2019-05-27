package ao3.br.rec.books.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ao3.br.rec.books.entity.User;

@Component
public class AuthProviderService implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		System.out.println(auth);
		String login = auth.getName();
		String senha = auth.getCredentials().toString();

		User userOK = null;
		List<User> users = userService.getAllUsers();
		
		//Autenticacao FAKE - apenas para validar o fluxo do sistema de autenticacao
		//Spring security
		for (User u : users) {
			if(u.getUserName().equalsIgnoreCase(login) && u.getPassword().equals(senha)) {
				userOK = new User();
				userOK.setPassword(senha);
				userOK.setUserName(login);
				userOK.setUser_id(u.getUserId());
				userOK.setUserId(u.getUserId());
				break;
			}
		}


		if(userOK != null) {
			 Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
			 UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, senha, new ArrayList<>());
			 token.setDetails(userOK);
			 return token;
		} else {
          throw new BadCredentialsException("Usuário inválido !");
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
}