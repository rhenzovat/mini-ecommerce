package demo.renzo.Mini_Ecommerce.service;

import demo.renzo.Mini_Ecommerce.model.Producto;
import demo.renzo.Mini_Ecommerce.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired //con este metodo inyetamos el reposiotio a service y poder usar jpaRepositorio que no ayuda a comuniar con la apa de datos
    private ProductoRepositorio productoRepositorio;


    @Override
    public List<Producto> listarEmpleado() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer id) {
        Producto producto = productoRepositorio.findById(id).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);//aqui hibernate guarda el prodcuto en caso sea null el id o lo actuliza si ya existe
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRepositorio.delete(producto);
    }
}
