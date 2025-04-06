package com.sevasangam.repository;

import com.sevasangam.model.Campaign;
import com.sevasangam.model.NGO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByNgo(NGO ngo);
    List<Campaign> findByCampaignStatus(String campaignStatus);
    List<Campaign> findByCategory(String category);
}

