package br.com.grupo5.trabalho_final.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.PedidoResponseDTO;
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

    // @Autowired
    // PedidoProdutoIdRepository ppIdRepo;

    // public String listaProdutos(Integer idCliente) {
    // String listaDeProdutos = "";
    // Pedido pedido = pedidoRepo.ultimoPedido(idCliente);
    // for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
    // Integer quantidade = produtoPed.getQuantidade();
    // Double preco = produtoPed.getProduto().getValor();
    // listaDeProdutos += produtoPed.getProduto().getNome() + " x " + quantidade + "
    // - R$ " + (quantidade * preco)
    // + "\r\n";
    // }
    // return listaDeProdutos + "\r\n\nR$ " + precoPedido(pedido.getCliente());
    // }

    // public Double precoPedido(Cliente cliente) {
    // Double valorTotal = 0.0;
    // Pedido pedido = pedidoRepo.ultimoPedido(cliente.getId

    // for (PedidoProduto produtoPed : pedido.getPedidoProdutos()) {
    // Integer quantidade = produtoPed.getQuantidade();
    // Double preco = produtoPed.getProduto().getValor();
    // valorTotal += quantidade * preco;
    // }
    // return valorTotal;
    // }

    public ResponseEntity<?> adicionarProduto(PedidoRequestDTO pedidoDTO) {
        Optional<Cliente> optionalCliente = clienteRepo.findById(pedidoDTO.getIdCliente());
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            Pedido pedido = new Pedido(cliente);
            Produto produto = produtoRepo.findById(pedidoDTO.getIdProduto()).get();
            PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, pedidoDTO.getQuantidade());

            pedido.getPedidoProdutos().add(pedidoProduto);
            produto.setEstoque(produto.getEstoque() - pedidoDTO.getQuantidade());
            produtoRepo.save(produto);
            pedidoRepo.save(pedido);
            System.out.println("LOGGY " + pedido.toString());

            PedidoProdutoId pedidoProdutoId = new PedidoProdutoId(produto.getId(), pedido.getId());
            pedidoProduto.setId(pedidoProdutoId);
            System.out.println("LOGGY " + pedidoProdutoId.toString());
            System.out.println("LOGGY " + pedidoProduto.toString());

            ppRepo.save(pedidoProduto);

            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(pedido.getValorTotal(),
                    pedido.getCliente().getId(),
                    pedido.isAtivo());
            return ResponseEntity.ok(pedidoResponseDTO);
        } else {
            return ResponseEntity.badRequest().body("Cliente não encontrado.");
        }
    }

    public ResponseEntity<?> updatePedidoById(Integer id, PedidoRequestDTO pedidoDTO) {
        Optional<Pedido> optionalPedido = pedidoRepo.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            Produto produto = produtoRepo.findById(pedidoDTO.getIdProduto()).get();
            PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, pedidoDTO.getQuantidade());
            pedido.getPedidoProdutos().add(pedidoProduto);
            produto.setEstoque(produto.getEstoque() - pedidoDTO.getQuantidade());
            produtoRepo.save(produto);
            pedidoRepo.save(pedido);
            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(pedido.getValorTotal(),
                    pedido.getCliente().getId(),
                    pedido.isAtivo());
            return ResponseEntity.ok(pedidoResponseDTO);
        } else {
            return ResponseEntity.badRequest().body("Pedido não encontrado.");
        }
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

    // public ResponseEntity<?> getAllPedidos(Integer idCliente) {
    // return ResponseEntity.ok(pedidoRepo.listaPedidos(idCliente));
    // }

    // private boolean verificarUltimoPedido(Integer idCliente) {
    // return pedidoRepo.existePedido(idCliente);
    // }

}
