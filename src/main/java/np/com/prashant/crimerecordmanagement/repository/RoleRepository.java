package np.com.prashant.crimerecordmanagement.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import np.com.prashant.crimerecordmanagement.models.ERole;
import np.com.prashant.crimerecordmanagement.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role>findByName(ERole name);
	
}
