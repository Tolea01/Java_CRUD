package org.example.interfaces;
import org.example.pojo.Tara;
import java.util.List;

public interface TaraService {
    Tara getTara(Long id);
    void createTara(Tara tara);
    void deleteTara(Long id);
    void updateTara(Long id, Tara tara);
    List<Tara> searchTari(String numeFilter, String codFilter, String limbaFilter, String monedaFilter);
}
