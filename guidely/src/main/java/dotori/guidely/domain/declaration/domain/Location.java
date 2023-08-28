package dotori.guidely.domain.declaration.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
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

    @OneToMany(mappedBy = "location")
    @JsonBackReference
    private List<Declaration> declarationList = new ArrayList<>();

    public void addDeclaration(Declaration declaration){ //편의 메소드
        this.declarationList.add(declaration);
    }
    @Builder
    public Location(@NonNull double latitude, @NonNull double longitude, @NonNull String address, String buildingName, LocationType type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.type = type;
    }
}
