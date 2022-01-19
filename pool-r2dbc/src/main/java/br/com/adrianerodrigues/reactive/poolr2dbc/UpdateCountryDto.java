package br.com.adrianerodrigues.reactive.poolr2dbc;

import lombok.Data;

@Data
public class UpdateCountryDto {
  private Integer id;
  private String name;
}
