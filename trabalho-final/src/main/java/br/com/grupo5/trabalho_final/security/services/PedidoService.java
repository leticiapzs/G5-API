package br.com.grupo5.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.Pedido;
import br.com.grupo5.trabalho_final.security.entities.PedidoProduto;
import br.com.grupo5.trabalho_final.security.repositories.PedidoProdutoRepository;
import br.com.grupo5.trabalho_final.security.repositories.PedidoRepository;
import br.com.grupo5.trabalho_final.security.repositories.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	PedidoProdutoRepository ppRepo;
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	public String listaProdutos (Integer idCliente) {
		String listaDeProdutos = "";
		Pedido pedido = pedidoRepo.ultimoPedido(idCliente);
		for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
			Integer quantidade = produtoPed.getQuantidade();
			Double preco = produtoPed.getProduto().getValor();
			listaDeProdutos += produtoPed.getProduto().getNome() + " x " + quantidade + " - R$ " + (quantidade*preco) + "\r\n";
		}
		return listaDeProdutos + "\r\n\nR$ " + precoPedido(pedido.getCliente());
	}
	
	public Double precoPedido (Cliente cliente) {
		Double valorTotal = 0.0;
		Pedido pedido = pedidoRepo.ultimoPedido(cliente.getId());
		for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
			Integer quantidade = ppRepo.findById(produtoPed.getId()).get().getQuantidade();
			Double preco = produtoPed.getProduto().getValor();
			valorTotal += quantidade * preco ;
		}
		return valorTotal;
	}
	
	public Pedido adicionarProduto (PedidoRequestDTO pedidoDTO) {
		Pedido pedidoExistente = pedidoRepo.ultimoPedido(pedidoDTO.getCliente().getId());
		if (!pedidoExistente.isAtivo() || pedidoExistente == null) {
			Pedido pedido = new Pedido(pedidoDTO.getCliente());
			PedidoProduto pedidoProduto = new PedidoProduto(pedido, pedidoDTO.getProduto(), pedidoDTO.getQuantidade());
			pedido.getPedidoProdutos().add(pedidoProduto);
			pedido.setValorTotal(precoPedido(pedidoDTO.getCliente()));
			pedidoRepo.save(pedido);
			return pedido;
		} else {
			PedidoProduto pedidoProduto = new PedidoProduto(pedidoExistente, pedidoDTO.getProduto(), pedidoDTO.getQuantidade());
			pedidoExistente.getPedidoProdutos().add(pedidoProduto);
			pedidoExistente.setValorTotal(precoPedido(pedidoDTO.getCliente()));
			pedidoRepo.save(pedidoExistente);
			return pedidoExistente;
		}	
	}

}
