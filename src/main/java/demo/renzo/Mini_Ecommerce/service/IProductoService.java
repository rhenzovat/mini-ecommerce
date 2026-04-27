package demo.renzo.Mini_Ecommerce.service;

import demo.renzo.Mini_Ecommerce.model.Producto;
import jakarta.persistence.Index;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarEmpleado();

    public Producto buscarProductoPorId(Integer id);

    public Producto guardarProducto(Producto producto);//recibimos un objeto d etipo prodcuot

    public void eliminarProducto(Producto producto);


}
