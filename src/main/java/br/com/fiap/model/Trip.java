package br.com.fiap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trip {
    private Long id;
    private LocalDate date;
    private String country;
    private String city;
    private String urlPhotos;
}
