package swith.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swith.backend.domain.Pose;

public interface PoseRepository extends JpaRepository<Pose,Long> {

}
