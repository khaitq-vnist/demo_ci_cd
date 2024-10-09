package vn.vnist.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vnist.project3.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
