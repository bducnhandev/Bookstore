package Tuan4.BuiDucNhanBai4.repositories;

import Tuan4.BuiDucNhanBai4.entities.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemInvoiceRepository extends JpaRepository<ItemInvoice, Long> {
}
