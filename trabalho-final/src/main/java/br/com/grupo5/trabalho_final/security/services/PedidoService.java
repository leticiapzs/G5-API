package br.com.grupo5.trabalho_final.security.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

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

            PedidoProdutoId pedidoProdutoId = new PedidoProdutoId(produto.getId(), pedido.getId());
            pedidoProduto.setId(pedidoProdutoId);

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

    public ResponseEntity<?> getAllPedidosByClienteId(Integer idCliente) {
        List<Pedido> pedidos = pedidoRepo.listaPedidos(idCliente);
        List<PedidoResponseDTO> pedidoDTOs = pedidos.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(pedidoDTOs);
    }

    private PedidoResponseDTO convertToDTO(Pedido pedido) {
        return new PedidoResponseDTO(pedido.getValorTotal(), pedido.getCliente().getId(), pedido.isAtivo());
    }
}
