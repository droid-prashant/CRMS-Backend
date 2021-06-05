package np.com.prashant.crimerecordmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import np.com.prashant.crimerecordmanagement.models.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailIgnoreCase(String email);

	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}

