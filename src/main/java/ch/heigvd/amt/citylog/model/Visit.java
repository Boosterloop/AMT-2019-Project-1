package ch.heigvd.amt.citylog.model;

import lombok.*;

/**
 * Defines a Visit's data (link between User and City)
 *
 * @author Alison Savary, Luc Wachter
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"user", "city", "startDate"})
public class Visit {
    @NonNull
    private int id;

    @NonNull
    private User user;

    @NonNull
    private City city;

    @NonNull
    private String startDate;

    private String endDate;
}
