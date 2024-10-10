package com.ti.interdisciplinar242.Configurations.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityFilter /*extends OncePerRequestFilter*/ {
/*
    @Autowired
    TokenService tokenService;
    @Autowired
    TesteInterface testeInterface;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validacaoToken(token);

        if(login != null){
            TesteModel testeModel = testeInterface.findByLogin(login).orElseThrow(() -> new RuntimeException("Não encontrado."));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(testeModel, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Autorização");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }*/
}
