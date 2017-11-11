package com.example.rabanales21.rabanales21;

/**
 * Traduce la respuesta obtenida de la BBDD para crear las reservas que seran mostradas en la app. </p>
 */

public class ConsultaReserva {

    private String sala;
    private String fecha_inicio;
    private String id_reserva;
    private String nombre_usuario;
    private String tipo_usuario;
    private String hora_inio;
    private String hora_fin;

    public ConsultaReserva(String sala, String fecha_inicio, String id_reserva, String nombre_usuario, String tipo_usuario, String hora_inio, String hora_fin) {

    /**
     * Constructor de consultas de reservas para mostarlas en la app </br>
     * @param sala La sala en la que se realizo la reserva
     * @param fecha_inicio La fecha en que se realizo la reserva
     * @param id_reserva ID de control de la reserva
     * @param hora_inio Hora a la que se inicia la reserva
     * @param hora_fin Hora a la que finaliza la reserva
     */

        this.sala = sala;
        this.fecha_inicio = fecha_inicio;
        this.id_reserva = id_reserva;
        this.nombre_usuario = nombre_usuario;
        this.tipo_usuario = tipo_usuario;
        this.hora_inio = hora_inio;
        this.hora_fin = hora_fin;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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
        if (nombre_usuario != null ? !nombre_usuario.equals(that.nombre_usuario) : that.nombre_usuario != null)
            return false;
        if (tipo_usuario != null ? !tipo_usuario.equals(that.tipo_usuario) : that.tipo_usuario != null)
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
        result = 31 * result + (nombre_usuario != null ? nombre_usuario.hashCode() : 0);
        result = 31 * result + (tipo_usuario != null ? tipo_usuario.hashCode() : 0);
        result = 31 * result + (hora_inio != null ? hora_inio.hashCode() : 0);
        result = 31 * result + (hora_fin != null ? hora_fin.hashCode() : 0);
        return result;
    }
}
