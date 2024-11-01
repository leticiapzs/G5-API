package br.com.grupo5.trabalho_final.security.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.grupo5.trabalho_final.security.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query(value = "SELECT * FROM pedido p WHERE p.cliente_id = :idcliente ORDER BY p.ped_cd_id DESC LIMIT 1", nativeQuery = true)
	Pedido ultimoPedido(@Param("idcliente") Integer idcliente);

	@Query(value = "SELECT * FROM pedido WHERE cliente_id = :idCliente", nativeQuery = true)
	List<Pedido> listaPedidos(@Param("idCliente") Integer idCliente);

	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM pedido p WHERE cliente_id = :idCliente) THEN true ELSE false END", nativeQuery = true)
	public boolean existePedido(Integer idCliente);
}
