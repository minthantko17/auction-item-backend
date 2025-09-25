package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Bid;

public interface BidService {
    Integer getBidSize();
    Page<Bid> getBids(Pageable pageable);
    Bid getBid(Long bidId);
    Bid save(Bid bid);
}
