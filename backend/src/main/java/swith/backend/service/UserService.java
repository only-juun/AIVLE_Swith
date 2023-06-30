package swith.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.domain.User;
import swith.backend.exception.ExceptionCode;
import swith.backend.exception.UserException;
import swith.backend.jwt.JwtTokenProvider;
import swith.backend.jwt.TokenInfo;
import swith.backend.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public User findUser(String email) {
        return userRepository.findByEmail(email).get();
    }


    @Transactional
    public User join(User user) {
        verifiedExistedEmail(user.getEmail());
        verifiedExistedSerialNumber(user.getSerialNumber());
        verifiedExistedPhoneNumber(user.getPhoneNumber());
        return userRepository.save(user);
    }

    @Transactional
    public TokenInfo login(String email, String password) {

        // Spring Security는 사용자 검증을 위해
        // encoding된 password와 그렇지 않은 password를 비교

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    private void verifiedExistedEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            throw new UserException(ExceptionCode.USER_EMAIL_EXISTS);
        }
    }

    private void verifiedExistedSerialNumber(String serialNumber) {
        Optional<User> findUser = userRepository.findBySerialNumber(serialNumber);
        if (findUser.isPresent()) {
            throw new UserException(ExceptionCode.USER_SERIAL_EXISTS);
        }
    }

    private void verifiedExistedPhoneNumber(String phoneNumber) {
        Optional<User> findUser = userRepository.findByPhoneNumber(phoneNumber);
        if (findUser.isPresent()) {
            throw new UserException(ExceptionCode.USER_PHONE_EXISTS);
        }
    }
}
