package br.com.grupo5.trabalho_final.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.grupo5.trabalho_final.security.embeddable.PedidoProdutoId;
import br.com.grupo5.trabalho_final.security.entities.Pedido;
import br.com.grupo5.trabalho_final.security.entities.PedidoProduto;
import br.com.grupo5.trabalho_final.security.entities.Produto;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoId>{
	
	@Query(value = "select pp.* from pedido_produto pp where pp.pedido_id = :pedido and pp.produto_id = :produto;", nativeQuery = true)
	public PedidoProduto findByBoth(Pedido pedido, Produto produto);

}
