package com.worldpay.codingtest;

import com.worldpay.codingtest.domain.Offer;
import com.worldpay.codingtest.exception.OfferNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    private OfferServiceImpl offerService;

    @Before
    public void init(){
        offerService = new OfferServiceImpl();
    }

    @Test
    public void createAOffer(){
        Offer offer = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));

        Assert.assertEquals(offerService.getOffers().size(), 0);
        offerService.createOffer(offer);
        Assert.assertEquals(offerService.getOffers().size(), 1);
    }

    @Test
    public void updateOffer(){
        Offer offer = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));
        offerService.createOffer(offer);
        Offer beforeUpdate = offerService.getOffer(1L);
        Assert.assertEquals(String.valueOf(beforeUpdate.getPrice()), "2.0");

        Offer updateOffer = createOffer(1L, "Water", "2 for 1", 10.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));
        offerService.updateOffer(updateOffer);
        Offer updatedOffer = offerService.getOffer(1L);
        Assert.assertEquals(String.valueOf(updatedOffer.getPrice()), "10.0");
    }

    @Test
    public void getOneOffer(){
        Offer newOffer = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));
        offerService.createOffer(newOffer);

        Offer offer = offerService.getOffer(1L);
        Assert.assertEquals(String.valueOf(offer.getId()), "1");
        Assert.assertEquals(offer.getTitle(), "Water");
        Assert.assertEquals(offer.getDescription(), "2 for 1");
        Assert.assertEquals(String.valueOf(offer.getPrice()), "2.0");
        Assert.assertEquals(offer.getCurrency(), "$");
        Assert.assertEquals(offer.getListedOn().toString(), "Fri Jul 20 00:00:00 BST 2018");
        Assert.assertEquals(offer.getExpiresOn().toString(), "Wed Aug 01 00:00:00 BST 2018");
    }

    @Test(expected = OfferNotFoundException.class)
    public void returnOfferNotFoundException() {
        Offer newOffer = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));

        offerService.createOffer(newOffer);
        offerService.getOffer(2L);
    }

    @Test
    public void getAllOffers(){
        Offer offer1 = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 8, 1));
        offerService.createOffer(offer1);
        Offer offer2 = createOffer(2L, "Oranges", "3 for 1", 3.0, "$", setUpDate(2018, 7, 25), setUpDate(2018, 7, 28));
        offerService.createOffer(offer2);

        List<Offer> offers = offerService.getOffers();
        Assert.assertEquals(offers.size(), 2);
    }

    @Test
    public void willCancelOfferWhenNotExpired(){
        Offer offer1 = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2019, 8, 1));
        offerService.createOffer(offer1);
        Assert.assertEquals(offerService.getOffers().size(), 1);

        offerService.cancelOffer(1L);
        Assert.assertEquals(offerService.getOffers().size(), 0);
    }

    @Test(expected = OfferNotFoundException.class)
    public void willNotCancelOfferWhenExpired(){
        Offer offer1 = createOffer(1L, "Water", "2 for 1", 2.0, "$", setUpDate(2018, 7, 20), setUpDate(2018, 7, 25));
        offerService.createOffer(offer1);
        offerService.cancelOffer(1L);
    }

    private Date setUpDate(int year, int month, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    private Offer createOffer(Long id, String title, String description, Double price, String currency, Date listedOn, Date expiresOn) {
        return new Offer(id, title, description, price, currency, listedOn, expiresOn);
    }
}
