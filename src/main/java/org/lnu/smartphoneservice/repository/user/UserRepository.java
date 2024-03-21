package org.lnu.smartphoneservice.repository.user;

import jakarta.transaction.Transactional;
import org.lnu.smartphoneservice.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    int removeOneById(Long id);
}
