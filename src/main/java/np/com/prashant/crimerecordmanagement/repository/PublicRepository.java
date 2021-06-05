package np.com.prashant.crimerecordmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.PublicRegistration;
@Repository
@EnableJpaRepositories
public interface PublicRepository extends JpaRepository<PublicRegistration, Long> {
	
	@Query(value="select * from public_details where remark='unassigned'", nativeQuery = true)
	public List<PublicRegistration> findAll();

}
