package ch.heigvd.amt.citylog.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class User {
    @NonNull
    private final String username;
    @NonNull
    private String password;
}
