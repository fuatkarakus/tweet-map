package com.advancedatabase.project.util.enums;

public enum CountryWOEID {

    SF(12587707L), US(23424977L), UK(23424975L), WW(1L);

    CountryWOEID(Long woeid) {
        this.woeid = woeid;
    }

    private final long woeid;

    public long getWoeid() {
        return woeid;
    }

}