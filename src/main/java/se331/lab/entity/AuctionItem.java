package se331.lab.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data // auto generate getter, setter, toString, equal, hasCode
@Builder // generate builder pattern
@Entity // mark class as JPA
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItem {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    Long id;
    String description;
    String type;

    @Builder.Default
    @OneToMany(mappedBy = "auctionItem")
    @JsonManagedReference
    List<Bid> bids = new ArrayList<Bid>();

    @OneToOne
    Bid successfulBid;
}
