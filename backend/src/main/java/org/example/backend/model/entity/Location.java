package org.example.backend.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Location extends AbstractEntity {
    @Column(columnDefinition="Decimal(10,8)")
    private Double latitude;

    @Column(columnDefinition="Decimal(10,8)")
    private Double longitude;

    public Location(Long id, Double latitude, Double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
