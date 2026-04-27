package demo.renzo.Mini_Ecommerce.repositorio;

import demo.renzo.Mini_Ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}
