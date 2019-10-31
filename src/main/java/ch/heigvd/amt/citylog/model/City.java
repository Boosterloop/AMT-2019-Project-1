package ch.heigvd.amt.citylog.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Defines a City's data
 *
 * @author Alison Savary, Luc Wachter
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class City {
    @NonNull
    private final int id;
    @NonNull
    private String name;
    @NonNull
    private int countryId;
}
