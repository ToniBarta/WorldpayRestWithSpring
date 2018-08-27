package com.worldpay.codingtest;

import com.worldpay.codingtest.domain.Offer;

import java.util.List;

public interface OfferService {

    long createOffer(Offer offer);

    Offer updateOffer(Offer offer);

    Offer getOffer(Long id);

    List<Offer> getOffers();

    void cancelOffer(Long id);
}
