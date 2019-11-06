package ch.heigvd.amt.citylog.model;

import lombok.*;

import java.util.Date;

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
    @NonNull
    private User user;

    @NonNull
    private City city;

    @NonNull
    private Date startDate;

    private Date endDate;
}
