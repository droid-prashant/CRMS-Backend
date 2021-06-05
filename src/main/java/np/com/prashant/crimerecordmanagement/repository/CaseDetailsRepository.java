package np.com.prashant.crimerecordmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.CaseDetails;

@Repository
public interface CaseDetailsRepository extends JpaRepository<CaseDetails, Long> {

	@Query(value="select * from case_details where ac_id=?1", nativeQuery = true)
	List<CaseDetails> findByAcId(Long acId);

}
