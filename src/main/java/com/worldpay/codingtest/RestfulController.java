package com.worldpay.codingtest;

import com.worldpay.codingtest.domain.Offer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestfulController {

    private OfferServiceImpl offerService;

    public RestfulController(){
        offerService = new OfferServiceImpl();
    }

    @RequestMapping("/healthy")
    public String checkHealth() {
        return "Application is up and running";
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/offer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public long createOffer(@RequestBody Offer offer){
        return offerService.createOffer(offer);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/offer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Offer updateOffer(@RequestBody Offer offer) {
        return offerService.updateOffer(offer);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/offer/{id}")
    public Offer getOffer(@PathVariable("id") Long id){
        return offerService.getOffer(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/offers")
    public List<Offer> getOffer(){
        return offerService.getOffers();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/offer/{id}")
    public void deleteOffer(@PathVariable("id") Long id) {
        offerService.cancelOffer(id);
    }

}
