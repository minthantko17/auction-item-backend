package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Bid;
import se331.lab.repository.BidRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class BidDaoDbImpl implements BidDao {
    final BidRepository bidRepository;

    @Override
    public Integer getBidSize(){
        return Math.toIntExact(bidRepository.count());
    }

    @Override
    public Page<Bid> getBids(Integer pageSize, Integer pageNumber) {
        pageSize = pageSize == null ? Math.toIntExact(bidRepository.count()) : pageSize;
        pageNumber = pageNumber == null ? 1 : pageNumber;
        return bidRepository.findAll(PageRequest.of(pageNumber-1, pageSize));
    }

    @Override
    public Bid getBid(Long bidId) {
        return bidRepository.findById(bidId).orElse(null);
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }
}
