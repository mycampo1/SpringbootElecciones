package mintic.ciclo4a.security.security;

import lombok.RequiredArgsConstructor;
import mintic.ciclo4a.security.filter.CustomAuthenticationFilter;
import mintic.ciclo4a.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        /*
        * customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        *
        * En el caso que se desee cambiar la ruta del login, ya que por defecto es "/login"
        * */

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/roles/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/roles/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/roles/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/permissions-roles/**").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/permissions-roles").hasAnyAuthority("Administrador");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/permissions-roles").hasAnyAuthority("Administrador");
        http.authorizeRequests().anyRequest().authenticated();

        //http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
