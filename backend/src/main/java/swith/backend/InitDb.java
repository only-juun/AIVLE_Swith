package swith.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.domain.User;
import swith.backend.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

//@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public void dbInit() {
            String password = "mong931!";
            String encodedPassword = passwordEncoder.encode(password);
            User user = User.builder()
                    .email("sis03017@naver.com")
                    .password(encodedPassword)
                    .nickname("운양")
                    .name("이한준")
                    .build();
            user.getRoles().add("USER");

            User user_1 = User.builder()
                    .email("dlgkswns@naver.com")
                    .password(encodedPassword)
                    .nickname("장기")
                    .name("피파")
                    .build();
            user.getRoles().add("USER");

            userRepository.save(user);
            userRepository.save(user_1);

            Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
            Optional<User> byId = userRepository.findById(user.getId());
            User user1 = byId.get();
            User user2 = byEmail.get();
            log.info("user1 = {}",user1);
            log.info("user2 = {}",user2);

        }




    }

}
