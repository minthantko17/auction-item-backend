package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.lab.entity.AuctionItem;
import se331.lab.entity.Bid;
import se331.lab.repository.AuctionItemRepository;
import se331.lab.repository.BidRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final AuctionItemRepository auctionItemRepository;
    final BidRepository bidRepository;

    private record AuctionSeed(String description, String type, List<Long> bid){}

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<AuctionSeed> autionSeeds = List.of(
                new AuctionSeed("Vintage Rolex Submariner Watch", "Luxury", List.of(5000L, 5500L, 6000L)),
                new AuctionSeed("Apple iPhone 15 Pro Max", "Electronics", List.of(1200L, 1300L, 1350L)),
                new AuctionSeed("Abstract Oil Painting by Local Artist", "Art", List.of(800L, 1000L)),
                new AuctionSeed("Lamborghini Aventador Model Car", "Collectible", List.of(300L, 450L)),
                new AuctionSeed("Antique Wooden Cabinet", "Furniture", List.of(1500L, 1700L, 2000L)),
                new AuctionSeed("PlayStation 5 Console", "Electronics", List.of(700L, 750L, 800L)),
                new AuctionSeed("Diamond Engagement Ring", "Jewelry", List.of(5000L, 5200L, 5500L, 6000L)),
                new AuctionSeed("Signed Basketball Jersey", "Sports Memorabilia", List.of(400L, 600L, 700L)),
                new AuctionSeed("MacBook Pro 16-inch", "Electronics", List.of(2000L, 2100L, 2300L)),
                new AuctionSeed("Rare First Edition Book", "Books", List.of())
        );

        for (AuctionSeed seed: autionSeeds){
            AuctionItem auctionItem = AuctionItem.builder()
                    .description(seed.description)
                    .type(seed.type)
                    .build();

            auctionItemRepository.save(auctionItem);

            List<Bid> bids = new ArrayList<>();
            for (Long amount: seed.bid ){
                Bid bid = Bid.builder()
                        .amount(amount)
                        .dateTime(new Date())
                        .auctionItem(auctionItem)
                        .build();
                bids.add(bid);
            }

            if(!bids.isEmpty()){
                bidRepository.saveAll(bids);

                Bid maxBid = bids.stream()
                        .max(Comparator.comparing(bid -> bid.getAmount()))
                        .orElse(null);

                auctionItem.setSuccessfulBid(maxBid);
                auctionItemRepository.save(auctionItem);
            }
        }

    }
}
