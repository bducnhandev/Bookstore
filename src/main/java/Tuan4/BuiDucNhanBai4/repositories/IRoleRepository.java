package Tuan4.BuiDucNhanBai4.repositories;

import Tuan4.BuiDucNhanBai4.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
    Role findRoleById(Long id);
}
