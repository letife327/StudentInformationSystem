package az.texnoera.studentinformationsystem.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataResult <T>{
    private List<T> data;
    private int size;
    private int page;
    private int totalPages;
}
