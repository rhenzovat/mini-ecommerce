package demo.renzo.Mini_Ecommerce.controller;

import demo.renzo.Mini_Ecommerce.exception.RecursoNoEncontrado;
import demo.renzo.Mini_Ecommerce.model.Producto;
import demo.renzo.Mini_Ecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mini")
@CrossOrigin(value = "http://localhost:3000") //para las peticion de de front end
public class ProductoController {
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService; //inyectamos servicce para poder comunicarnos con los servicios

    //aqui agregamos lo metodos
    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        var productos = productoService.listarEmpleado();
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("prodcuto a agregar"+producto);
        return productoService.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id){
        Producto producto= productoService.buscarProductoPorId(id);
        if(producto == null){
            throw new RecursoNoEncontrado(("no se encontro el id" +id));
        }
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> acualizarProdcuto(@PathVariable Integer id, @RequestBody Producto productoRecibido){
        Producto producto = productoService.buscarProductoPorId(id);
        if(producto == null){
            throw new RecursoNoEncontrado(("no se encontro el id" +id));
        }
        producto.setNombre(productoRecibido.getNombre());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setStock(productoRecibido.getStock());
        productoService.guardarProducto(producto);
        return ResponseEntity.ok(producto);

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Integer id){
        Producto producto = productoService.buscarProductoPorId(id);
        if(producto == null)
            throw new RecursoNoEncontrado("El id no se encontro" + id);
        productoService.eliminarProducto(producto);
        //jspn la resspuieta al eliminar
        Map<String, Boolean> respusta = new HashMap<>();
        respusta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respusta);


    }
}
