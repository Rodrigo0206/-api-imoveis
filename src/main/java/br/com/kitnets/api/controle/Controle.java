package br.com.kitnets.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.kitnets.api.modelo.Pessoa;
import br.com.kitnets.api.repositorio.Repositorio;
import br.com.kitnets.api.servico.Servico;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);

    }
    @GetMapping("/api")
    public List<Pessoa> selecionar(){
        return acao.findAll();
    }
    @GetMapping("/api/{codigo}")
    public Pessoa selecionarPeloCodigo(@PathVariable int codigo){
        return acao.findByCodigo(codigo);
    }

    @PutMapping("/api")
    public Pessoa editar(@RequestBody Pessoa obj){
        return acao.save(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public void remover(@PathVariable int codigo){
        Pessoa obj = selecionarPeloCodigo(codigo);

        acao.delete(obj);
    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }
    
    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("tatiana");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("l");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("a");
    }
    
    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("a");
    }
    
    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){     
        return acao.idadeMaiorIgual(18);   
    }

    @GetMapping("")
    public String mensagem(){   
        return"heeeello Word";
    }
    
    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Seja bem vindo (a)";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "Seja bem vindo (a)" + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    
}