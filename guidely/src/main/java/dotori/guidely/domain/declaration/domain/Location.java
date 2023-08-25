package dotori.guidely.domain.declaration.domain;

import lombok.NonNull;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private double latitude;
    @NonNull
    private double longitude;

    @NonNull
    private String address; //도로명 주소

    private String buildingName;

    @Enumerated(EnumType.STRING)
    private LocationType type;
}
