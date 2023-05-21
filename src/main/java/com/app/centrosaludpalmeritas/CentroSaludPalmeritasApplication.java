package com.app.centrosaludpalmeritas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuracion Spring Boot
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.app.centrosaludpalmeritas.repositories"})
@EnableWebSecurity
public class CentroSaludPalmeritasApplication {
    /**
     * metodo para especificar las vistas al incio y
     * cerrado de sesion
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/ingresos/").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/ingresos/", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();

        return http.build();
    }

    /**
     * metodo main de la aplicacion
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CentroSaludPalmeritasApplication.class, args);
    }

}
