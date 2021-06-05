package np.com.prashant.crimerecordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import np.com.prashant.crimerecordmanagement.entities.CriminalPicture;

@Repository
public interface CriminalPictureRepository extends JpaRepository<CriminalPicture, Long> {

}
