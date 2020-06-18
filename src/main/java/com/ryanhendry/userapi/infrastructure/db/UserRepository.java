package com.ryanhendry.userapi.infrastructure.db;

import com.ryanhendry.userapi.domain.entity.dao.UserDao;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao, UUID> {
  Optional<UserDao> findByUsername(String username);
}
