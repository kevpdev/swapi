package fr.kevpdev.swapi.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResultDTO<T> {

  private Integer count;
  private String next;
  private String previous;
  private List<T> results;
}
