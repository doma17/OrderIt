package inu.amigo.orderIt.repository;

import inu.amigo.orderIt.domain.item.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
