package dotori.guidely.domain.declaration.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @Builder
    public Location(@NonNull double latitude, @NonNull double longitude, @NonNull String address, String buildingName, LocationType type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.type = type;
    }
}
