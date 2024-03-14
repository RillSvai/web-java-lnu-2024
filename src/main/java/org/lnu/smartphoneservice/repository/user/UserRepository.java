package org.lnu.smartphoneservice.repository.user;

import org.lnu.smartphoneservice.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
