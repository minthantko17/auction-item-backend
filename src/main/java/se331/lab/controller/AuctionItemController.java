package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                                                  @RequestParam(value = "_page", required = false)Integer pageNumber,
                                                  @RequestParam(value = "description", required = false)String description,
                                                  @RequestParam(value = "type", required = false)String type){
        pageSize = pageSize == null ? auctionItemService.getAuctionItemSize() : pageSize;
        pageNumber = pageNumber == null ? 1 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        Page<AuctionItem> pageOutput;
        if(description != null && type != null) {
            pageOutput = auctionItemService.getAuctionItemsByDescriptionAndType(description, type, pageable);
        } else if (description != null) {
            pageOutput = auctionItemService.getAuctionItemsByDescription(description, pageable);
        } else if(type != null){
            pageOutput = auctionItemService.getAuctionItemsByType(type, pageable);
        }else{
            pageOutput = auctionItemService.getAuctionItems(pageable);
        }

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
