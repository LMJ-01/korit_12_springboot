package com.korit12.cardatabase.service;

import com.korit12.cardatabase.domain.AppUser;
import com.korit12.cardatabase.domain.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    // field 선언
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        UserBuilder builder = null;
        if(user.isPresent()) {  // 이하의 실행문이 실행된다면 user에 AppUser 객체가 있다는 의미 -> 아직 user는 Optional 자료형이니까요
            AppUser currentUser = user.get();   // Optional에서 제네릭으로 존재하는 객체를 빼내는 메서드가 .get()입니다.
            builder = User.withUsername(username)
                    .password(currentUser.getPassword())    // AppUser에 @Data 달아놨습니다.
                    .roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("해당 username을 가진 사용자를 찾지 못했습니다.");
        }
        return builder.build();
    }
}
