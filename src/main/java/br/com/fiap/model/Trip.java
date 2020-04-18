package br.com.fiap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trip {
    private Long id;
    private String date;
    private String country;
    private String city;
    private String urlPhotos;
}
