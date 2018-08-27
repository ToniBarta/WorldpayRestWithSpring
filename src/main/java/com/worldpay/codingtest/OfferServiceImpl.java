package com.worldpay.codingtest;

import com.worldpay.codingtest.domain.Offer;
import com.worldpay.codingtest.exception.OfferNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OfferServiceImpl implements OfferService {

    private List<Offer> offers;

    public OfferServiceImpl(){
        offers = new ArrayList<>();
    }

    public long createOffer(Offer offer) {
        offers.add(offer);
        return offer.getId();
    }

    public Offer updateOffer(Offer offer) {
        Optional<Offer> offerOptional = offers.stream()
                .filter(o -> o.getId().equals(offer.getId()))
                .findFirst();
        if (!offerOptional.isPresent()) {
            throw new OfferNotFoundException();
        }
        Offer offerToUpdate = offerOptional.get();
        offerToUpdate.setTitle(offer.getTitle());
        offerToUpdate.setDescription(offer.getDescription());
        offerToUpdate.setCurrency(offer.getCurrency());
        offerToUpdate.setPrice(offer.getPrice());
        offerToUpdate.setExpiresOn(offer.getExpiresOn());
        offerToUpdate.setListedOn(offer.getListedOn());
        return offerToUpdate;
    }

    public Offer getOffer(Long id) {
        Optional<Offer> offer = offers.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
        if (!offer.isPresent()) {
            throw new OfferNotFoundException();
        }
        return offer.get();
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void cancelOffer(Long id) {
        System.out.println(offers.get(0).getExpiresOn().toString());
        System.out.println(new Date().toString());

        Optional<Offer> offerOptional = offers.stream()
                .filter(o -> o.getId().equals(id) && o.getExpiresOn().after(new Date()))
                .findFirst();
        if (!offerOptional.isPresent()) {
            throw new OfferNotFoundException();
        }
        offers.remove(offerOptional.get());
    }
}
