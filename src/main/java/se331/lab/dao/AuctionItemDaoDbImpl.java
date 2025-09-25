package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<AuctionItem> getAuctionItems(Pageable pageable){
        return auctionItemRepository.findAll(pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable){
        return auctionItemRepository.findByDescriptionContainingIgnoreCase(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByType(String type, Pageable pageable){
        return auctionItemRepository.findByTypeContainingIgnoreCase(type, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDesctiptionAndType(String description, String type, Pageable pageable){
        return auctionItemRepository.findByDescriptionContainingIgnoreCaseAndTypeContainingIgnoreCase(description, type, pageable);
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
