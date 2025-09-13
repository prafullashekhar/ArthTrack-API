package com.prafull.ArthTrack.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prafull.ArthTrack.domain.entity.User;
import com.prafull.ArthTrack.domain.jpaRepository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        try{
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestTokenHeader.substring(7); // remove "Bearer "
            String userIdStr = authUtil.getUserIdFromToken(token);

            if(userIdStr != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Long userId = Long.parseLong(userIdStr);
                User user = userRepository.findById(userId).orElseThrow();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                // ✅ Enable Hibernate Filter with userId
                Session session = entityManager.unwrap(Session.class);
                session.enableFilter("userFilter").setParameter("userId", userId);
            }

            filterChain.doFilter(request, response);

        }catch(Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);
        }finally {
            Session session = entityManager.unwrap(Session.class);
            if (session.isOpen()) {
                session.disableFilter("userFilter");
            }
        }
        
    }
}
