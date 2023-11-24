package com.kowwo.model;

public record BookingStatus(int economyUsage, int economyRevenue, int premiumUsage, int premiumRevenue) {

    @Override
    public String toString() {
        return String.format("Usage Economy: %s (EUR %s)\n" +
                "Usage Premium: %s (EUR %s)", economyUsage, economyRevenue, premiumUsage, premiumRevenue);
    }
}
