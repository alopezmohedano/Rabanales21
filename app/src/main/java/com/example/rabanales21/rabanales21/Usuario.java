package com.example.rabanales21.rabanales21;

/**
 * Created by Crcama on 26/10/2017.
 */

public class Usuario {
    private String nombreUsuario;
    private Integer nombreEmpresa;
    private String codigoUsuario;

    public Usuario(String nombreUsuario, Integer nombreEmpresa, String codigoUsuario) {
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

    public Integer getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(Integer nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (getNombreUsuario() != null ? !getNombreUsuario().equals(usuario.getNombreUsuario()) : usuario.getNombreUsuario() != null)
            return false;
        if (getNombreEmpresa() != null ? !getNombreEmpresa().equals(usuario.getNombreEmpresa()) : usuario.getNombreEmpresa() != null)
            return false;
        return getCodigoUsuario() != null ? getCodigoUsuario().equals(usuario.getCodigoUsuario()) : usuario.getCodigoUsuario() == null;

    }

    @Override
    public int hashCode() {
        int result = getNombreUsuario() != null ? getNombreUsuario().hashCode() : 0;
        result = 31 * result + (getNombreEmpresa() != null ? getNombreEmpresa().hashCode() : 0);
        result = 31 * result + (getCodigoUsuario() != null ? getCodigoUsuario().hashCode() : 0);
        return result;
    }
}
