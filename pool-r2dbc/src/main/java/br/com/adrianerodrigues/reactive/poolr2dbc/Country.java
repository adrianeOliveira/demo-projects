package br.com.adrianerodrigues.reactive.poolr2dbc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
  @Id
  private Integer id;
  private String name;
}
