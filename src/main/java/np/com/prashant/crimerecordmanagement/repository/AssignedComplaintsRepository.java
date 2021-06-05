package np.com.prashant.crimerecordmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.AssignedComplaints;
import np.com.prashant.crimerecordmanagement.entities.PoliceStaffRegistration;

@Repository
@EnableJpaRepositories
public interface AssignedComplaintsRepository extends JpaRepository<AssignedComplaints, Long> {

	
	@Query(value="select * from assigned_complaints where is_active= 1 and remark='assigned' and preg_id=?1", nativeQuery = true)
	public List<AssignedComplaints> findAssignedComplaintsByPregId(Long id);
	
	@Query(value="select *from assigned_complaints where remark='assigned'", nativeQuery = true)
	public List<AssignedComplaints>findAll();
	
	
}
