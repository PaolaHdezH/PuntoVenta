
package modelo;


public class Productos {
    int id;
    String nombre;
    String codigoBarras;
    double precio;
    int stock;
    int FK_PROVEEDOR;

    public Productos() {
    }

    public Productos(int id, String nombre, String codigoBarras, double precio, int stock, int FK_PROVEEDOR) {
        this.id = id;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.stock = stock;
        this.FK_PROVEEDOR = FK_PROVEEDOR;
    }

    public Productos(String nombre, String codigoBarras, double precio, int stock, int FK_PROVEEDOR) {
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.stock = stock;
        this.FK_PROVEEDOR = FK_PROVEEDOR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getFK_PROVEEDOR() {
        return FK_PROVEEDOR;
    }

    public void setFK_PROVEEDOR(int FK_PROVEEDOR) {
        this.FK_PROVEEDOR = FK_PROVEEDOR;
    }

    
    
    
    
}
