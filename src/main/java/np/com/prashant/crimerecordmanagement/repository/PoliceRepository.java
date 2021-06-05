package np.com.prashant.crimerecordmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.AssignedComplaints;
import np.com.prashant.crimerecordmanagement.entities.PoliceStaffRegistration;
import np.com.prashant.crimerecordmanagement.models.User;
@Repository
public interface PoliceRepository extends JpaRepository<PoliceStaffRegistration, Long> {

	@Query(value="select * from police_record where rank=?1", nativeQuery = true)
	public List<PoliceStaffRegistration>findByrank(String rank);
	
//
//	@Query(value="select * from police_record where is_active= 1 and obj_id=?1", nativeQuery = true)
//	public List<PoliceStaffRegistration> findByUserId(Long id);
	Optional<PoliceStaffRegistration> findByobjId(Long id);
	
	
}
