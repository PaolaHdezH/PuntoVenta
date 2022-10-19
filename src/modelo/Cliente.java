/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Cliente {
    private String id;
    private String nombre;
    private String rfc;
    private String correoElectronico;

    public Cliente(String id, String nombre, String rfc, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
        this.correoElectronico = correoElectronico;
    }

    public Cliente(String id, String nombre, String rfc ) {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
    }

    

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String correoElectronico) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.correoElectronico = correoElectronico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
}
