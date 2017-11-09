package com.example.rabanales21.rabanales21;

import android.view.View;

/**
 * Created by usuario on 06/11/2017.
 */

public class ConsultaReserva {
private String sala;
    private String fecha_inicio;
    private String id_reserva;
    private String hora_inio;
    private String hora_fin;

    public ConsultaReserva(String sala, String fecha_inicio, String id_reserva, String hora_inio, String hora_fin) {
        this.sala = sala;
        this.fecha_inicio = fecha_inicio;
        this.id_reserva = id_reserva;
        this.hora_inio = hora_inio;
        this.hora_fin = hora_fin;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(String fecha_fin) {
        this.id_reserva = fecha_fin;
    }

    public String getHora_inio() {
        return hora_inio;
    }

    public void setHora_inio(String hora_inio) {
        this.hora_inio = hora_inio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultaReserva that = (ConsultaReserva) o;

        if (sala != null ? !sala.equals(that.sala) : that.sala != null) return false;
        if (fecha_inicio != null ? !fecha_inicio.equals(that.fecha_inicio) : that.fecha_inicio != null)
            return false;
        if (id_reserva != null ? !id_reserva.equals(that.id_reserva) : that.id_reserva != null)
            return false;
        if (hora_inio != null ? !hora_inio.equals(that.hora_inio) : that.hora_inio != null)
            return false;
        return hora_fin != null ? hora_fin.equals(that.hora_fin) : that.hora_fin == null;

    }

    @Override
    public int hashCode() {
        int result = sala != null ? sala.hashCode() : 0;
        result = 31 * result + (fecha_inicio != null ? fecha_inicio.hashCode() : 0);
        result = 31 * result + (id_reserva != null ? id_reserva.hashCode() : 0);
        result = 31 * result + (hora_inio != null ? hora_inio.hashCode() : 0);
        result = 31 * result + (hora_fin != null ? hora_fin.hashCode() : 0);
        return result;
    }

}
