package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.AuctionItem;
import se331.lab.service.AuctionItemService;

@Controller
@RequiredArgsConstructor
public class AuctionItemController {
    final AuctionItemService auctionItemService;

    @GetMapping("/auction-items")
    public ResponseEntity<?>  getAuctionItemsList(@RequestParam(value = "_limit", required = false)Integer pageSize,
                                                  @RequestParam(value = "_page", required = false)Integer pageNumber){
        Page<AuctionItem> pageOutput = auctionItemService.getAuctionItems(pageSize, pageNumber);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/auction-items/{id}")
    public ResponseEntity<?> getAuctionItemById(@PathVariable(value = "id")Long id){
        AuctionItem auctionItem = auctionItemService.getAuctionItem(id);
        if(auctionItem != null){
            return ResponseEntity.ok(auctionItem);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction Item Not Found");
        }
    }

    @PostMapping("/auction-items")
    public ResponseEntity<?> addAuctionItem(@RequestBody AuctionItem auctionItem){
        AuctionItem auctionItemOutput =  auctionItemService.save(auctionItem);
        return ResponseEntity.ok(auctionItemOutput);
    }
}
