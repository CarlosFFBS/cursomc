package com.carlosfabiano.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.carlosfabiano.cursomc.domain.Categoria;
import com.carlosfabiano.cursomc.domain.Cidade;
import com.carlosfabiano.cursomc.domain.Cliente;
import com.carlosfabiano.cursomc.domain.Endereco;
import com.carlosfabiano.cursomc.domain.Estado;
import com.carlosfabiano.cursomc.domain.ItemPedido;
import com.carlosfabiano.cursomc.domain.Pagamento;
import com.carlosfabiano.cursomc.domain.PagamentoBoleto;
import com.carlosfabiano.cursomc.domain.PagamentoCartao;
import com.carlosfabiano.cursomc.domain.Pedido;
import com.carlosfabiano.cursomc.domain.Produto;
import com.carlosfabiano.cursomc.domain.Enums.EstadoPagamento;
import com.carlosfabiano.cursomc.domain.Enums.TipoCliente;
import com.carlosfabiano.cursomc.repositories.CategoriaRepository;
import com.carlosfabiano.cursomc.repositories.CidadeRepository;
import com.carlosfabiano.cursomc.repositories.EnderecoRepository;
import com.carlosfabiano.cursomc.repositories.ItemPedidoRepository;
import com.carlosfabiano.cursomc.repositories.PagamentoRepository;
import com.carlosfabiano.cursomc.repositories.PedidoRepository;
import com.carlosfabiano.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
	
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente c1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "123.456.789-86", TipoCliente.PESSOAFISICA);
		c1.getTelefones().addAll(Arrays.asList("3231.3231", "3113.3223"));
		Cliente c2 = new Cliente(null, "Carlos Silva", "carlos@gmail.com", "456.456.123-86", TipoCliente.PESSOAJURIDICA);
		c2.getTelefones().addAll(Arrays.asList("9999.8888","8888.7777"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "ap 203", "Jardim", "38777012", c1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sl 800", "Centro", "38220804", c1, cid2);
		Endereco e3 = new Endereco(null, "Avenida Sul", "200", "sl 100", "Botucatu", "38220804", c2, cid3);
		
		enderecoRepo.saveAll(Arrays.asList(e1, e2, e3));	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 09:19"), c1, e1 );
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:35"), c2, e2 );
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2017 00:00"));
		ped2.setPagamento(pgto2);
		
		c1.getPedidos().addAll(Arrays.asList(ped1));
		c2.getPedidos().addAll(Arrays.asList(ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
