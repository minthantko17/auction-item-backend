package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.AuctionItem;

public interface AuctionItemService {
    Integer getAuctionItemSize();
    Page<AuctionItem> getAuctionItems(Integer pageSize, Integer pageNumber);
    AuctionItem getAuctionItem(Long auctionItemId);
    AuctionItem save(AuctionItem auctionItem);
}
