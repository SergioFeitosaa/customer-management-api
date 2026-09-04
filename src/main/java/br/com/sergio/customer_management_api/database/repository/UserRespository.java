package br.com.sergio.customer_management_api.database.repository;

import br.com.sergio.customer_management_api.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRespository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
