package br.edu.ifpb.dac.trainee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.trainee.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String username);

}
