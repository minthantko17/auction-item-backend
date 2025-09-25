package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.AuctionItem;

public interface AuctionItemDao {
    Integer getAuctionItemSize();
    Page<AuctionItem> getAuctionItems(Pageable pageable);
    Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable);
    AuctionItem getAuctionItem(Long auctionItemId);
    AuctionItem save(AuctionItem auctionItem);
}
