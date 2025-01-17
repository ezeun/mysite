package mysite.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mysite.repository.UserRepository;
import mysite.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder =passwordEncoder;
	}

	public void join(UserVo userVo) {
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		userRepository.join(userVo);
	}

	public UserVo getUser(Long no) {
		return userRepository.findById(no);
	}
	
	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(String email) {
		UserVo userVo = userRepository.findByEmail(email, UserVo.class);
		userVo.setPassword("");
		return userVo;
	}

	public void update(UserVo userVo) {
		userRepository.update(userVo);
	}
}
