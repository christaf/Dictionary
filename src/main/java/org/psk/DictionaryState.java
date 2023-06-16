package org.psk;

public enum DictionaryState {
    POLISH_ENGLISH("POLISH->ENGLISH"),
    ENGLISH_POLISH("ENGLISH->POLISH");
    private final String description;

    private DictionaryState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
