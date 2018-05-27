package com.api.apisigi.controller;

import com.api.apisigi.entity.Departamento;
import com.api.apisigi.exception.ResourceNotFoundExcption;
import com.api.apisigi.repository.IRDepartamento;
import com.api.apisigi.repository.IREdificio;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    @Qualifier("departamentoRepo")
    private IRDepartamento departamentorpeo;
    @Autowired
    @Qualifier("edificioRepo")
    private IREdificio edificiorepo;

    //#ROUTE METHODS
    //#POST METHOD: INSERCION DEPARTAMENTO + IDEDIFICIO
    @PostMapping("/edificio/{idEdificio}/ndepartamento")
    @ResponseBody
    @JsonFormat
    public Departamento createEdificio(@PathVariable(value = "idEdificio") String idEdificio,
                                       @Valid @RequestBody Departamento departamento) {
        return departamentorpeo.save(
                edificiorepo.findById(idEdificio).map(edificio -> {
                    departamento.setEdificio(edificio);
                    return departamento;
                }).orElseThrow(() -> new ResourceNotFoundExcption("edificioId " + idEdificio + " not found")
                ));
    }

    //#GET METHOD: OBTENCION DE LISTADO EDIFICIOS
    //#LISTADO DE DEPARTAMENTOS POR ID EDIFICIO
    @GetMapping("/edifio/{edificioId}/departamentos/listdeptos")
    @ResponseBody
    @JsonFormat
    public Page<Departamento> getAllComunasByRegionID(@PathVariable(value = "edificioId") String edificioId,
                                                      Pageable pageable) {
        return departamentorpeo.findByIdEdificio(edificioId, pageable);
    }

    //#LISTADO DE DEPARTAMENTOS
    @GetMapping("/departamentos")
    @ResponseBody
    @JsonFormat
    public List<Departamento> getAll() {
        return departamentorpeo.findAll();
    }

    //#PUT METHOD: ACTUALIZACION DE DEPARTAMENTO
    @PutMapping("/edificio/{edificioId}/departamento/{departamentoId}")
    public Departamento updateDepartamento(@PathVariable(value = "edificioId") String edificioId,
                                     @PathVariable(value = "departamentoId") String departamentoId,
                                     @Valid @RequestBody Departamento departamentorequest) {
        if (!edificiorepo.existsById(edificioId)) {
            throw new ResourceNotFoundExcption("ID " + edificioId + " not found");
        }
        return departamentorpeo.findById(departamentoId).map(departamento -> {
            departamento.setIdDepto(departamentorequest.getIdDepto());
            departamento.setCantDorm(departamentorequest.getCantDorm());
            departamento.setCantDorm(departamentorequest.getCantDorm());
            departamento.setCantDorm(departamentorequest.getCantDorm());
            departamento.setCantDorm(departamentorequest.getCantDorm());

            return departamentorpeo.save(departamento);
        }).orElseThrow(() -> new ResourceNotFoundExcption("CommentId " + departamentoId + "not found"));
    }

    //#DELETE METHOD: ELIMINACION DE DEPARTAMENTO
    @DeleteMapping("/edificio/{edificioId}/departamento/{departamentoId}")
    @ResponseBody
    @JsonFormat
    public ResponseEntity<?> deleteComment(@PathVariable(value = "edificioId") String edificioId,
                                           @PathVariable(value = "departamentoId") String departamentoId) {
        if (!edificiorepo.existsById(edificioId)) {
            throw new ResourceNotFoundExcption("PostId " + edificioId + " not found");
        }

        return departamentorpeo.findById(departamentoId).map(departamento -> {
            departamentorpeo.delete(departamento);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundExcption("CommentId " + departamentoId + " not found"));
    }
}