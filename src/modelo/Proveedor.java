
package modelo;

/**************************************************************************
 * Nombre de la clase¨:Proveedor
 *
 * Informacion de la version:
 *
 * Fecha
 *
 * Copyright
 **************************************************************************/

public class Proveedor {
    
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String compania;
    private String telefono;
    private String correoElectronico;
    private String InstitucionBancaria;
    private String clabe;

    public Proveedor(String id, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String compania, String telefono, String correoElectronico, String InstitucionBancaria, String clabe) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.compania = compania;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.InstitucionBancaria = InstitucionBancaria;
        this.clabe = clabe;
    }
    
    

    public Proveedor(String id, String nombre, String compania) {
        this.id = id;
        this.nombre = nombre;
        this.compania = compania;
    }
    
    public Proveedor(String nombre, String apellidoPaterno, String apellidoMaterno,
                    String direccion, String compania, String telefono,
                    String correoElectronico, String InstitucionBancaria, String clabe) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.compania = compania;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.InstitucionBancaria = InstitucionBancaria;
        this.clabe = clabe;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCompania() {
        return compania;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getInstitucionBancaria() {
        return InstitucionBancaria;
    }

    public String getClabe() {
        return clabe;
    }
    
    
}
