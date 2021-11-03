package com.janett.miproyectofinal.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private Contrato contrato;
    private int idContrato;
    private int nroPago;
    private String fecha;
    private double importe;

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Pago() {}

    public Pago(int id, Contrato contrato, int idContrato, int nroPago, String fecha, double importe) {
        this.id = id;
        this.contrato = contrato;
        this.idContrato = idContrato;
        this.nroPago = nroPago;
        this.fecha = fecha;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public Contrato getcontrato() {
        return contrato;
    }

    public void setcontrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
