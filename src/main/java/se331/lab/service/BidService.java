package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Bid;

public interface BidService {
    Integer getBidSize();
    Page<Bid> getBids(Integer pageSize, Integer pageNumber);
    Bid getBid(Long bidId);
    Bid save(Bid bid);
}
