package com.kowwo.service;

import com.kowwo.model.BookingStatus;
import com.kowwo.providers.GuestsProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OccupancyService {

    public BookingStatus calculateRevenue(int economyRooms, int premiumRooms) {
        int[] guests = GuestsProvider.getGuests();
        Arrays.sort(guests);

        int economyUsage = 0;
        int premiumUsage = 0;
        int economyRevenue = 0;
        int premiumRevenue = 0;

        List<Integer> economyGuests = new ArrayList<>();
        List<Integer> premiumGuests = new ArrayList<>();

        splitGuests(guests, economyGuests, premiumGuests);

        //calculate premium revenue
        for (int i = premiumGuests.size() - 1; i >=0; i--) {
            if (premiumRooms > 0) {
                premiumRevenue += premiumGuests.get(i);
                premiumRooms--;
                premiumUsage++;
            }
        }

        //will someone get an upgrade
        boolean moreEconomyGuestsThanEconomyRooms = economyGuests.size() > economyRooms;
        int economyGuestsInEconomyRooms = 0;
        //calculate economy revenue for economy guests who don't get an upgrade
        if(moreEconomyGuestsThanEconomyRooms && premiumRooms > 0) {
            for (int i = economyRooms - 1; i >= 0; i--) {
                economyRevenue += economyGuests.get(i);
                economyRooms--;
                economyUsage++;
                economyGuestsInEconomyRooms++;
            }
        }
        for (int i = economyGuests.size() - 1; i >= economyGuestsInEconomyRooms; i--) {
            //economy guests upgraded
            if(moreEconomyGuestsThanEconomyRooms && premiumRooms > 0) {
                premiumRevenue += economyGuests.get(i);
                premiumRooms--;
                premiumUsage++;
            } else if(economyRooms > 0) {
                //regular economy guests
                economyRevenue += economyGuests.get(i);
                economyRooms--;
                economyUsage++;
            }
        }

        return new BookingStatus(economyUsage, economyRevenue, premiumUsage, premiumRevenue);
    }

    private void splitGuests(int[] guests, List<Integer> economyGuests, List<Integer> premiumGuests) {
        for (int guest: guests) {
            if(guest < 100) {
                economyGuests.add(guest);
            } else {
                premiumGuests.add(guest);
            }
        }
    }
}
