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
import se331.lab.entity.Bid;
import se331.lab.service.BidService;

@Controller
@RequiredArgsConstructor
public class BidController {
    final BidService bidService;

    @GetMapping("/bids")
    public ResponseEntity<?> getBidsList(@RequestParam(value = "_limit", required = false ) Integer pageSize,
                                         @RequestParam(value = "_page", required = false ) Integer pageNumber){
        pageSize = pageSize == null ? bidService.getBidSize() : pageSize;
        pageNumber = pageNumber == null ? 1 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page< Bid > pageOutput = bidService.getBids(pageable);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));

        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/bids?{id}")
    public ResponseEntity<?> getBidById(@PathVariable Long id){
        Bid bid = bidService.getBid(id);
        if(bid != null){
            return ResponseEntity.ok(bid);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid Not Found");
        }
    }

    @PostMapping("/bids")
    public ResponseEntity<?> addBid(@RequestBody Bid bid){
        Bid bidOutput = bidService.save(bid);
        return ResponseEntity.ok(bidOutput);
    }
}
