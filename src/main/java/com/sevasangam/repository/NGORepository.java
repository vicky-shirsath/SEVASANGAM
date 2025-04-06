package com.sevasangam.repository;

import com.sevasangam.model.NGO;
import com.sevasangam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface NGORepository extends JpaRepository<NGO, Long> {
    Optional<NGO> findByUser(User user);
    List<NGO> findByIsVerified(boolean isVerified);
    List<NGO> findByNgoType(String ngoType);
}

