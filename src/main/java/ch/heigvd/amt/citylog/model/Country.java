package ch.heigvd.amt.citylog.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Defines a Country's data
 *
 * @author Alison Savary, Luc Wachter
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Country {
    @NonNull
    private String code;

    @NonNull
    private String name;
}
