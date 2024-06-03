package Tuan4.BuiDucNhanBai4.repositories;

import Tuan4.BuiDucNhanBai4.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
