package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.AuctionItemDao;
import se331.lab.entity.AuctionItem;

@Service
@RequiredArgsConstructor
public class AuctionItemServiceImpl implements AuctionItemService{
    final AuctionItemDao auctionItemDao;

    @Override
    public Integer getAuctionItemSize() {
        return auctionItemDao.getAuctionItemSize();
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Pageable pageable) {
        return auctionItemDao.getAuctionItems(pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable){
        return auctionItemDao.getAuctionItemsByDescription(description,pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByType(String type, Pageable pageable){
        return auctionItemDao.getAuctionItemsByType(type,pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDescriptionAndType(String description, String type, Pageable page){
        return auctionItemDao.getAuctionItemsByDesctiptionAndType(description,type,page);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidAmountLessThan(Long bidAmount, Pageable pageable){
        return auctionItemDao.getAuctionItemsBySuccessfulBidAmountLessThan(bidAmount, pageable);
    }

    @Override
    public AuctionItem getAuctionItem(Long auctionItemId) {
        return auctionItemDao.getAuctionItem(auctionItemId);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem) {
        return auctionItemDao.save(auctionItem);
    }
}
