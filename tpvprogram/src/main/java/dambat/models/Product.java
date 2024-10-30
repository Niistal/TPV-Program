package dambat.models;

public class Product {
    private int id_prod;
    private String name;
    private double precio;
    private int idKategoria;

    public Product(int id_prod, String name, double precio, int idKategoria) {
        this.id_prod = id_prod;
        this.name = name;
        this.precio = precio;
        this.idKategoria = idKategoria;
    }

    public int getId_prod() {
        return id_prod;
    }

    public String getName() {
        return name;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public void setIdKategoria(int idKategoria) {
        this.idKategoria = idKategoria;
    }

    public int getIdKategoria() {
        return idKategoria;
    }
}
