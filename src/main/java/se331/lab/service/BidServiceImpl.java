package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.dao.BidDao;
import se331.lab.entity.Bid;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService{
    final BidDao bidDao;

    @Override
    public Integer getBidSize(){
        return bidDao.getBidSize();
    }

    @Override
    public Page<Bid> getBids(Integer pageSize, Integer pageNumber) {
        return bidDao.getBids(pageSize, pageNumber);
    }

    @Override
    public Bid getBid(Long bidId) {
        return bidDao.getBid(bidId);
    }

    @Override
    public Bid save(Bid bid) {
        return bidDao.save(bid);
    }
}
