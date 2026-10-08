package fr.kevpdev.swapi.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResultDTO<T> {

  public Integer count;
  public String next;
  public String previous;
  public List<T> results;
}
