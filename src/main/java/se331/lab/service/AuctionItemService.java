package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.AuctionItem;

public interface AuctionItemService {
    Integer getAuctionItemSize();
    Page<AuctionItem> getAuctionItems(Pageable pageable);
    Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable);
    Page<AuctionItem> getAuctionItemsByType(String type, Pageable pageable);
    Page<AuctionItem> getAuctionItemsByDescriptionAndType(String description, String type, Pageable pageable);
    AuctionItem getAuctionItem(Long auctionItemId);
    AuctionItem save(AuctionItem auctionItem);
}
