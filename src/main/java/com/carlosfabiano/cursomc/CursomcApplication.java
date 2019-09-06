package com.carlosfabiano.cursomc;

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
import com.carlosfabiano.cursomc.domain.Produto;
import com.carlosfabiano.cursomc.domain.Enums.TipoCliente;
import com.carlosfabiano.cursomc.repositories.CategoriaRepository;
import com.carlosfabiano.cursomc.repositories.CidadeRepository;
import com.carlosfabiano.cursomc.repositories.EnderecoRepository;
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
	private EnderecoRepository EnderecoRepo;

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
		
		//c1.getEnderecos().addAll(Arrays.asList(e1, e2));
		//c2.getEnderecos().addAll(Arrays.asList(e3));
		
		EnderecoRepo.saveAll(Arrays.asList(e1, e2, e3));		
		
	}

}
