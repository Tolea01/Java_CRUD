package org.example.services;

import org.example.interfaces.TaraService;
import org.example.pojo.Tara;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaraServiceImpl implements TaraService {
    private List<Tara> tariList = new ArrayList<>(Arrays.asList(
            new Tara(1L, "România", "RO", "Română", "Leu"),
            new Tara(2L, "Germania", "DE", "Germană", "Euro"),
            new Tara(3L, "Franța", "FR", "Franceză", "Euro")
    ));

    @Override
    public Tara getTara(Long id) {
        return tariList.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void createTara(Tara tara) {

        tariList.add(tara);
    }


    @Override
    public void deleteTara(Long id) {
        tariList.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void updateTara(Long id, Tara tara) {
        deleteTara(id);
        createTara(tara);
    }

    @Override
    public List<Tara> searchTari(String numeFilter, String codFilter, String limbaFilter, String monedaFilter) {

        return tariList.stream()
                .filter(t ->
                        t.getNume().toLowerCase().contains(numeFilter.toLowerCase()) &&
                                t.getCod().toLowerCase().contains(codFilter.toLowerCase()) &&
                                t.getLimbaOficiala().toLowerCase().contains(limbaFilter.toLowerCase()) &&
                                t.getMoneda().toLowerCase().contains(monedaFilter.toLowerCase()))
                .collect(Collectors.toList());
    }


}
