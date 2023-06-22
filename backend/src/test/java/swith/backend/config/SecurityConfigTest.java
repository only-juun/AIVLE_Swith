package swith.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SecurityConfigTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 패스워드_암호화() throws Exception {
        //given
        String password = "남환준NamHwanJun";
        //when
        String encodePassword = passwordEncoder.encode(password);
        //then
        assertThat(encodePassword).startsWith("{");
        assertThat(encodePassword).contains(("{bcrypt}"));
        assertThat(encodePassword).isNotEqualTo(password);
    }
}