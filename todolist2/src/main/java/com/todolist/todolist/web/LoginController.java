package com.todolist.todolist.web;

import com.todolist.todolist.domain.AccountCredentials;
import com.todolist.todolist.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // 여기서 토큰 생성하고 응답의 Authorization 헤더로 전송해주는 로직을 작성할 예정입니다.
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        Authentication auth = authenticationManager.authenticate(creds);

        // 토큰 생성하는 JwtService의 getToken() 메서드를 호출하여 그 결과값을 대입 -> 지역변수로 쓰이겠네요.
        String jwts = jwtService.getToken(auth.getName());  // 이건 Java 내에서만 만들어진거지 외부에서 볼 수 없습니다.

        // 28 번 라인의 결과 jwt가 String 자료형으로 저장되었습니다. 이를 Headers의 authorization 부분에 업로드해줘야 합니다.
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization")
                .build();
    }
}
