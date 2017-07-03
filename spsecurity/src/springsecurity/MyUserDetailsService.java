package springsecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	//利用传入的 username 从数据库中获取用户信息, 然后封装成一个 UserDetails 对象返回. 
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//利用传入的 username 从数据库表中获取对应的记录
		System.out.println("从数据库中获取 " + username + " 对应的数据表记录.");
		
		//以下属性实际上都是从数据库中获取的.
		String password = null;
		if("user".equals(username)){
			password = "e14576586777603bd62a8ade7d10661a";
		}else if("admin".equals(username)){
			password = "b594510740d2ac4261c1b2fe87850d08";
		}
		
		//该用户是否是可用的
		boolean enabled = true;
		//用户是否没有过期
		boolean accountNonExpired = true;
		//凭证是否过期
		boolean credentialsNonExpired = true;
		//账户是否被锁定
		boolean accountNonLocked = true;
		//该该用户所拥有的权限
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		if("admin".equals(username)){
			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		
		//admin: b594510740d2ac4261c1b2fe87850d08
		//user: e14576586777603bd62a8ade7d10661a
		
		User user = new User(username, password, enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked, authorities);
		
		return user;
	}
	
	public static void main(String[] args) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String password = passwordEncoder.encodePassword("123456", "user");
		System.out.println(password);
	}

}
