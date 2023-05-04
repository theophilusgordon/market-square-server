package com.theophilusgordon.marketsquareserver.filter;

import com.theophilusgordon.marketsquareserver.service.UserDetailsServiceImpl;
import com.theophilusgordon.marketsquareserver.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> authHeader = Optional.ofNullable(request.getHeader("Authorization"));

        authHeader.filter(h -> h.startsWith("Bearer "))
            .map(h -> h.substring(7))
            .ifPresent(token -> {
                Optional<String> usernameOptional = Optional.ofNullable(jwtUtils.extractUsername(token));

                usernameOptional.filter(username -> SecurityContextHolder.getContext().getAuthentication() == null)
                    .ifPresent(username -> {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if (jwtUtils.validateToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    });
            });

        filterChain.doFilter(request, response);
    }
}
