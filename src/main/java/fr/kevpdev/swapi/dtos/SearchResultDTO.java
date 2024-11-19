package fr.kevpdev.swapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResultDTO <T> {

    public Integer count;
    public String next;
    public String previous;
    public List<T> results;

}
