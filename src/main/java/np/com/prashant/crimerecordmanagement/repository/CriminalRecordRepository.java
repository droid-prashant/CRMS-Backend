package np.com.prashant.crimerecordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.CriminalRecord;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord, Long> {

}
