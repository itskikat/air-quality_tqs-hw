package deti.tqs.fbarros.airqualityapp.repository;

import deti.tqs.fbarros.airqualityapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {
}
