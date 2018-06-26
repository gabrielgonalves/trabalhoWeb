/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Comentario;
import entidade.Disciplina;
import entidade.Topico;
import entidade.Usuario;
import gerenciador.GerenciadorDisciplinas;
import gerenciador.GerenciadorUsuarios;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utilidades.Sessao;

/**
 *
 * @author gabriel
 */
@ManagedBean
@SessionScoped
public class DisciplinaBean {

    private Disciplina disciplina;
    private Topico topico;
    private Usuario usuario;
    private Comentario comentario;

    private List<Disciplina> disciplinas;

    public DisciplinaBean() {
        comentario = new Comentario();
        disciplina = new Disciplina();
        topico = new Topico();
        usuario = new Usuario();
        disciplinas = GerenciadorDisciplinas.listar();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public List<Topico> getTopicos(){
        return GerenciadorDisciplinas.buscaTopicosDisciplina(disciplina.getId());
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String cadastrar() {
        GerenciadorDisciplinas.cadastrar(disciplina);
        disciplina = new Disciplina();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Disciplina cadastrada com sucesso!"));
        return null;
    }
    
    public String cadastrarTopico(){
        topico.getAutor().setId(Sessao.getInstance().getUsuarioLogado());
        GerenciadorDisciplinas.cadastrarTopico(topico, disciplina);
        return "topico.xhtml?faces-redirect=true";
    }
    
    public void cadastrarDisciplinaUsuario(){
        GerenciadorUsuarios.inserirDisciplinaUsuario(usuario, disciplina);
    }
    
    public List<Disciplina> getDisciplinasNaoCadastradas(){
        return GerenciadorUsuarios.buscaDisciplinasUsuarioNaoCadastrado(Sessao.getInstance().getUsuarioLogado());
    }
    
    public List<Disciplina> getDisciplinasUsuario(){
        return GerenciadorUsuarios.buscaDisciplinasUsuario(Sessao.getInstance().getUsuarioLogado());
    }
    
    public Usuario buscaUsuarioPorID(int id){
        return GerenciadorUsuarios.buscaUsuarioPorId(id);
    }
    
    public Usuario buscaUsuarioPorID(){
        return GerenciadorUsuarios.buscaUsuarioPorId(topico.getAutor().getId());
    }
    
    public List<Comentario> getComentarios(){
        return GerenciadorDisciplinas.buscaComentariosTopico(topico);
    }
    
    public String inserirComentario(){
        comentario.getAutor().setId(Sessao.getInstance().getUsuarioLogado());
        GerenciadorDisciplinas.inserirComentario(topico, comentario);
        comentario = new Comentario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Comentário feito com sucesso!"));
        return null;
    }
}
