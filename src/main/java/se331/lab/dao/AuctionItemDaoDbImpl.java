package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.entity.AuctionItem;
import se331.lab.repository.AuctionItemRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class AuctionItemDaoDbImpl implements AuctionItemDao{
    final AuctionItemRepository auctionItemRepository;

    @Override
    public Integer getAuctionItemSize(){
        return Math.toIntExact(auctionItemRepository.count());
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Integer pageSize, Integer pageNumber){
        pageSize = pageSize == null ? Math.toIntExact(auctionItemRepository.count()) : pageSize;
        pageNumber = pageNumber == null ? 1 : pageNumber;
        return auctionItemRepository.findAll(PageRequest.of(pageNumber-1, pageSize));
    }

    @Override
    public AuctionItem getAuctionItem(Long auctionItemId){
        return auctionItemRepository.findById(auctionItemId).orElse(null);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem){
        return auctionItemRepository.save(auctionItem);
    }
}
