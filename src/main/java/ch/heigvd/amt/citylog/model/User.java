package ch.heigvd.amt.citylog.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Defines a User's data
 *
 * @author Alison Savary, Luc Wachter
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class User {
    @NonNull
    private final String username;

    @NonNull
    private String password;
}
