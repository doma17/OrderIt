package inu.amigo.orderIt.domain.item.repository;

import inu.amigo.orderIt.domain.item.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}
