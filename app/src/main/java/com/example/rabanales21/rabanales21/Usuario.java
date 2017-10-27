package com.example.rabanales21.rabanales21;

/**
 * Created by Crcama on 26/10/2017.
 */

public class Usuario {
    private String nombreUsuario;
    private String nombreEmpresa;
    private Integer codigoUsuario;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String nombreEmpresa, Integer codigoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.nombreEmpresa = nombreEmpresa;
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (nombreUsuario != null ? !nombreUsuario.equals(usuario.nombreUsuario) : usuario.nombreUsuario != null)
            return false;
        if (nombreEmpresa != null ? !nombreEmpresa.equals(usuario.nombreEmpresa) : usuario.nombreEmpresa != null)
            return false;
        return codigoUsuario != null ? codigoUsuario.equals(usuario.codigoUsuario) : usuario.codigoUsuario == null;

    }

    @Override
    public int hashCode() {
        int result = nombreUsuario != null ? nombreUsuario.hashCode() : 0;
        result = 31 * result + (nombreEmpresa != null ? nombreEmpresa.hashCode() : 0);
        result = 31 * result + (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return result;
    }
}
