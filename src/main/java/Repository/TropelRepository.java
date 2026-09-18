package Repository;

import Model.Tropel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TropelRepository extends JpaRepository<Tropel,Long>, JpaSpecificationExecutor<Tropel> {
    boolean existsByName(String name);
}
