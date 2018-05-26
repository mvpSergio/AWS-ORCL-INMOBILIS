package com.api.apisigi.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "BOLETA_HONORARIO", schema = "SIGIADMIN", catalog = "")
public class BoletaHonorario {
    private String idBoleta;
    private String descripcion;
    private String idDocumento;
    private Documento documentoByIdDocumento;
    private Collection<CapacidadEconomica> capacidadEconomicasByIdBoleta;

    @Id
    @Column(name = "ID_BOLETA")
    public String getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(String idBoleta) {
        this.idBoleta = idBoleta;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "ID_DOCUMENTO")
    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoletaHonorario that = (BoletaHonorario) o;
        return Objects.equals(idBoleta, that.idBoleta) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(idDocumento, that.idDocumento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idBoleta, descripcion, idDocumento);
    }

    @ManyToOne
    @JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", nullable = false)
    public Documento getDocumentoByIdDocumento() {
        return documentoByIdDocumento;
    }

    public void setDocumentoByIdDocumento(Documento documentoByIdDocumento) {
        this.documentoByIdDocumento = documentoByIdDocumento;
    }

    @OneToMany(mappedBy = "boletaHonorarioByIdBoleta")
    public Collection<CapacidadEconomica> getCapacidadEconomicasByIdBoleta() {
        return capacidadEconomicasByIdBoleta;
    }

    public void setCapacidadEconomicasByIdBoleta(Collection<CapacidadEconomica> capacidadEconomicasByIdBoleta) {
        this.capacidadEconomicasByIdBoleta = capacidadEconomicasByIdBoleta;
    }
}
