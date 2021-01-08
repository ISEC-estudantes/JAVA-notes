package pd.exemplos.rest_api.getfile.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter  extends OncePerRequestFilter {
    private final String HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String authorizationHeaderValue = request.getHeader(HEADER);
        if (authorizationHeaderValue != null && Token.validateToken(authorizationHeaderValue)){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(Token.getUsernameByToken(authorizationHeaderValue), null , authorities);
            SecurityContextHolder.getContext().setAuthentication(uPAT);
        }
    }
}
