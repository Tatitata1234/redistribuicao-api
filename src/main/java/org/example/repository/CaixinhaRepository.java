package org.example.repository;

import org.example.model.entity.Caixinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixinhaRepository extends JpaRepository<Caixinha, Long> {
}
