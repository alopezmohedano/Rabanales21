package com.example.rabanales21.rabanales21;

/**
 * Bean de Salas </p>
 */

public class Sala {
    private String nombre;
    private Integer codigoSala;
    private String descripcion;

    public Sala() {
    }

    public Sala(String nombre, Integer codigoSala, String descripcion) {
        this.nombre = nombre;
        this.codigoSala = codigoSala;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(Integer codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sala)) return false;

        Sala sala = (Sala) o;

        if (getNombre() != null ? !getNombre().equals(sala.getNombre()) : sala.getNombre() != null)
            return false;
        if (getCodigoSala() != null ? !getCodigoSala().equals(sala.getCodigoSala()) : sala.getCodigoSala() != null)
            return false;
        return getDescripcion() != null ? getDescripcion().equals(sala.getDescripcion()) : sala.getDescripcion() == null;

    }

    @Override
    public int hashCode() {
        int result = getNombre() != null ? getNombre().hashCode() : 0;
        result = 31 * result + (getCodigoSala() != null ? getCodigoSala().hashCode() : 0);
        result = 31 * result + (getDescripcion() != null ? getDescripcion().hashCode() : 0);
        return result;
    }
}
