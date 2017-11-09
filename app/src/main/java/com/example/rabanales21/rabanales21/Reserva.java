package com.example.rabanales21.rabanales21;

/**
 * Created by Alvaro on 07/11/2017.
 */

class Reserva {
    String cod_r;
    String cod_s;
    String cod_u;
    String nombre_usuario;
    String inicio;
    String fin;

    public Reserva() {
    }

    public Reserva(String cod_r, String cod_s, String cod_u, String nombre_usuario, String inicio, String fin) {
        this.cod_r = cod_r;
        this.cod_s = cod_s;
        this.cod_u = cod_u;
        this.nombre_usuario = nombre_usuario;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCod_u() {
        return cod_u;
    }

    public void setCod_u(String cod_u) {
        this.cod_u = cod_u;
    }

    public String getCod_r() {
        return cod_r;
    }

    public void setCod_r(String cod_r) {
        this.cod_r = cod_r;
    }

    public String getCod_s() {
        return cod_s;
    }

    public void setCod_s(String cod_s) {
        this.cod_s = cod_s;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserva reserva = (Reserva) o;

        if (cod_r != null ? !cod_r.equals(reserva.cod_r) : reserva.cod_r != null) return false;
        if (cod_s != null ? !cod_s.equals(reserva.cod_s) : reserva.cod_s != null) return false;
        if (cod_u != null ? !cod_u.equals(reserva.cod_u) : reserva.cod_u != null) return false;
        if (nombre_usuario != null ? !nombre_usuario.equals(reserva.nombre_usuario) : reserva.nombre_usuario != null)
            return false;
        if (inicio != null ? !inicio.equals(reserva.inicio) : reserva.inicio != null) return false;
        return fin != null ? fin.equals(reserva.fin) : reserva.fin == null;

    }

    @Override
    public int hashCode() {
        int result = cod_r != null ? cod_r.hashCode() : 0;
        result = 31 * result + (cod_s != null ? cod_s.hashCode() : 0);
        result = 31 * result + (cod_u != null ? cod_u.hashCode() : 0);
        result = 31 * result + (nombre_usuario != null ? nombre_usuario.hashCode() : 0);
        result = 31 * result + (inicio != null ? inicio.hashCode() : 0);
        result = 31 * result + (fin != null ? fin.hashCode() : 0);
        return result;
    }
}
