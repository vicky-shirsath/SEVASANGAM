package com.sevasangam.repository;

import com.sevasangam.model.Donation;
import com.sevasangam.model.Donor;
import com.sevasangam.model.NGO;
import com.sevasangam.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonor(Donor donor);
    List<Donation> findByNgo(NGO ngo);
    List<Donation> findByCampaign(Campaign campaign);
}

