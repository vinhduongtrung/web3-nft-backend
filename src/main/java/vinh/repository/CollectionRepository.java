package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
