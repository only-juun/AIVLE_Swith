package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import swith.backend.config.SecurityUtil;
import swith.backend.domain.User;
import swith.backend.dto.UserInfoByTokenDto;
import swith.backend.dto.UserLoginRequestDto;
import swith.backend.dto.UserSignUpRequestDto;
import swith.backend.jwt.TokenInfo;
import swith.backend.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/user")
    public ResponseEntity<UserInfoByTokenDto> getUserByEmail() {
        User user = userService.findUser(SecurityUtil.getLoginUsername());
        UserInfoByTokenDto userInfoByTokenDto = UserInfoByTokenDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .serialNumber(user.getSerialNumber())
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(userInfoByTokenDto);
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity singUp(@Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        String encodedPassword = passwordEncoder.encode(userSignUpRequestDto.getPassword());
        User user = User.builder()
                .name(userSignUpRequestDto.getName())
                .nickname(userSignUpRequestDto.getNickname())
                .password(encodedPassword)
                .serialNumber(userSignUpRequestDto.getSerialNumber())
                .phoneNumber(userSignUpRequestDto.getPhoneNumber())
                .email(userSignUpRequestDto.getEmail())
                .build();
        user.getRoles().add("USER");
        userService.join(user);
        return null;
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        TokenInfo tokenInfo = userService.login(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());

        return tokenInfo;
    }

    // TODO: 회원정보 수정

    // TODO: 비밀번호 초기화

    // TODO: 회원탈퇴

    // TODO: 회원정보 조회


}
