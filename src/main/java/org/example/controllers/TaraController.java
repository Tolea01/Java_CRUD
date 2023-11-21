package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.example.interfaces.TaraService;
import org.example.pojo.Tara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaraController {
    @Autowired
    private TaraService taraService;

    @PostMapping(value = "/tara")
    public ResponseEntity createTara(@RequestBody Tara tara) {
        taraService.createTara(tara);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/tara/{id}")
    public ResponseEntity getTara(@PathVariable Long id) {
        Tara tara = taraService.getTara(id);
        HttpStatus status = (tara == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(tara, status);
    }

    @PutMapping(value = "/tara/{id}")
    public ResponseEntity updateTara(@PathVariable Long id, @RequestBody Tara tara) {
        Tara existingTara = taraService.getTara(id);
        if (existingTara != null) {
            taraService.updateTara(id, tara);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/tara/{id}")
    ResponseEntity patchPerson(@PathVariable Long id, @RequestBody JsonPatch
            patchOperations) throws JsonPatchException, JsonProcessingException {
        Tara tara = taraService.getTara(id);
        if (tara != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode patchedTaraJsonNode =
                    patchOperations.apply(objectMapper.valueToTree(tara));
            Tara patchedPerson = objectMapper.treeToValue(patchedTaraJsonNode, Tara.class);
            taraService.updateTara(id, patchedPerson);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value = "/tara/{id}")
    public ResponseEntity deleteTara(@PathVariable Long id) {
        if (taraService.getTara(id) != null) {
            taraService.deleteTara(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/tari")
    public ResponseEntity<List<Tara>> searchTari(@RequestParam(required = false, name = "nume", defaultValue = "") String nume,
                                                 @RequestParam(required = false, name = "cod", defaultValue = "") String cod,
                                                 @RequestParam(required = false, name = "limba", defaultValue = "") String limba,
                                                 @RequestParam(required = false, name = "moneda", defaultValue = "") String moneda) {
        List<Tara> taraList = taraService.searchTari(nume, cod, limba, moneda);
        HttpStatus httpStatus = taraList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity(taraList, httpStatus);
    }
}
