package ch.heigvd.amt.citylog.model;

import lombok.*;

/**
 * Defines a Visit's data (link between User and City)
 *
 * @author Alison Savary, Luc Wachter
 */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Visit {
    private final int id;

    @NonNull
    private User user;

    @NonNull
    private City city;

    @NonNull
    private String startDate;

    private String endDate;
}
