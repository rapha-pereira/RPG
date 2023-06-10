package rpg;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Personagem {
    private String nome, descricao;
    private int nivel, idade, forca, vitalidade, destreza, poder, pontosIniciais = 10;
    private Arma armaPrimaria, armaSecundaria;
    private ArrayList<Habilidade> habilidades;
    
    
    public Personagem(String nome, String descricao, int idade, Arma arma, int forca, int vitalidade, int destreza, int poder) {
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        definirArmaPersonagem(arma);
        this.nivel = 1;
        this.forca = forca;
        this.vitalidade = vitalidade;
        this.destreza = destreza;
        this.poder = poder;
    }
    
    public void definirArmaPersonagem(Arma arma) {
        if(arma.getPosicaoArma().equalsIgnoreCase("primaria")){
            this.armaPrimaria = arma;
        }else {
            this.armaSecundaria = arma;
        }
    }
    
    public void preencherAtributos() {
        this.forca = preencherUmAtributo("Força: ", pontosDisponiveis);
        this.poder = preencherUmAtributo("Poder: ", pontosDisponiveis);
        this.destreza = preencherUmAtributo("Destreza: ", pontosDisponiveis);
        this.vitalidade = preencherUmAtributo("Vitalidade: ", pontosDisponiveis);
    }
    
     public int preencherUmAtributo(String atributo, int pontosDisponiveis) {   
        while(true) {
            int valorAtributo = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos que deseja atribuir para " + atributo));
            if (valorAtributo > 0 && valorAtributo <= pontosDisponiveis) {
                return valorAtributo;
            } else {
                JOptionPane.showMessageDialog(
            null, "O valor do atributo dever ser entre 1 e 10");
            }
        }
    }

    public String getNome() {
        return this.nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public int getNivel() {
        return this.nivel;
    }
    public int getIdade() {
        return this.idade;
    }
    public int getForca() {
        return this.forca;
    }
    public int getVitadidade() {
        return this.vitalidade;
    }
    public int getDestreza() {
        return this.destreza;
    }
    public int getPoder() {
        return this.poder;
    }
    
    public Arma getArmaPrimaria() {
        return this.armaPrimaria;
    }
    public Arma getArmaSecundaria() {
        return this.armaSecundaria;
    }
    
    public ArrayList<Habilidade> getHabilidades() {
        return this.habilidades;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }
    public void setVitadidade(int vitalidade) {
        this.vitalidade = vitalidade;
    }
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
    public void setPoder(int poder) {
        this.poder = poder;
    }
    
    public void setArmaPrimaria(Arma armaPrimaria) {
        this.armaPrimaria = armaPrimaria;
    }
    public void setArmaSecundaria(Arma armaSecundaria) {
        this.armaSecundaria = armaSecundaria;
    }
    
    public void inserirHabilidade(Habilidade habilidade) {
        this.habilidades.add(habilidade);
    }
    
}
