package br.com.grupo5.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;
import br.com.grupo5.trabalho_final.security.embeddable.PedidoProdutoId;
import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.Pedido;
import br.com.grupo5.trabalho_final.security.entities.PedidoProduto;
import br.com.grupo5.trabalho_final.security.entities.Produto;
import br.com.grupo5.trabalho_final.security.repositories.ClienteRepository;
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

	@Autowired
	ClienteRepository clienteRepo;

	public String listaProdutos(Integer idCliente) {
		String listaDeProdutos = "";
		Pedido pedido = pedidoRepo.ultimoPedido(idCliente);
		for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
			Integer quantidade = produtoPed.getQuantidade();
			Double preco = produtoPed.getProduto().getValor();
			listaDeProdutos += produtoPed.getProduto().getNome() + " x " + quantidade + " - R$ " + (quantidade * preco)
					+ "\r\n";
		}
		return listaDeProdutos + "\r\n\nR$ " + precoPedido(pedido.getCliente());
	}

	public Double precoPedido(Cliente cliente) {
		Double valorTotal = 0.0;
		Pedido pedido = pedidoRepo.ultimoPedido(cliente.getId());
		for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
			Integer quantidade = ppRepo.findById(produtoPed.getId()).get().getQuantidade();
			Double preco = produtoPed.getProduto().getValor();
			valorTotal += quantidade * preco;
		}
		return valorTotal;
	}

	public ResponseEntity<?> adicionarProduto(PedidoRequestDTO pedidoDTO) {
		
		if (!verificarUltimoPedido(pedidoDTO.getIdCliente()) || !pedidoRepo.ultimoPedido(pedidoDTO.getIdCliente()).isAtivo()) {
			Pedido pedido = new Pedido(clienteRepo.findById(pedidoDTO.getIdCliente()).get());
			Produto produto = produtoRepo.findById(pedidoDTO.getIdProduto()).get();
			PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, pedidoDTO.getQuantidade());
			produto.setEstoque(produto.getEstoque() - pedidoDTO.getQuantidade());
			produtoRepo.save(produto);
			pedidoRepo.save(pedido);
			pedidoProduto.setId(new PedidoProdutoId(pedido.getId(), produto.getId()));
			pedido.getPedidoProdutos().add(pedidoProduto);
			ppRepo.save(pedidoProduto);
			return ResponseEntity.ok(pedido);
		} else {
			Pedido pedidoExistente = pedidoRepo.ultimoPedido(pedidoDTO.getIdCliente());
			Produto produto = produtoRepo.findById(pedidoDTO.getIdProduto()).get();
			PedidoProduto pedidoProduto = new PedidoProduto(pedidoExistente, produto, pedidoDTO.getQuantidade());
			produto.setEstoque(produto.getEstoque() - pedidoDTO.getQuantidade());
			produtoRepo.save(produto);
			pedidoRepo.save(pedidoExistente);
			pedidoProduto.setId(new PedidoProdutoId(pedidoExistente.getId(), produto.getId()));
			pedidoExistente.getPedidoProdutos().add(pedidoProduto);
			ppRepo.save(pedidoProduto);
			return ResponseEntity.ok(pedidoExistente);
		}
	}

	public ResponseEntity<?> updatePedidoById(Integer id, PedidoRequestDTO pedidoDTO) {
		Pedido pedido = pedidoRepo.findById(id).get();
		if (pedido != null) {
			PedidoProduto pedidoProduto = new PedidoProduto(pedido,
					produtoRepo.findById(pedidoDTO.getIdProduto()).get(), pedidoDTO.getQuantidade());
			pedido.getPedidoProdutos().add(pedidoProduto);
			pedido.setValorTotal(precoPedido(pedido.getCliente()));
			pedidoRepo.save(pedido);
			return ResponseEntity.ok(pedido);
		}
		return ResponseEntity.badRequest().body("Pedido não encontrado.");
	}

	public boolean pedidoDelete(Integer id) {
		Pedido pedido = pedidoRepo.findById(id).get();
		if (pedido != null) {
			pedido.setAtivo(false);
			pedidoRepo.save(pedido);
			return true;
		}
		return false;
	}

	public ResponseEntity<?> getAllPedidos(Integer idCliente) {
		return ResponseEntity.ok(pedidoRepo.listaPedidos(idCliente));
	}

	private boolean verificarUltimoPedido(Integer idCliente) {
		return pedidoRepo.existePedido(idCliente);
	}

}
