import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Personagem {
    private String nome, descricao;
    private Integer nivel, idade, forca, vitalidade, destreza, poder, pontosDisponiveis, xp, xpExcedente;
    private Arma armaPrimaria, armaSecundaria;
    private ArrayList<Habilidade> habilidades = new ArrayList<>();
    
    
    public Personagem(
        String nome, 
        String descricao, 
        Integer idade,
        Integer destreza,
        Integer vitalidade,
        Integer poder,
        Integer forca,
        Integer pontosDisponiveis,
        Arma arma
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.idade = idade;
        this.destreza = destreza;
        this.vitalidade = vitalidade;
        this.forca = forca;
        this.vitalidade = vitalidade;
        this.destreza = destreza;
        this.poder = poder;
        this.nivel = 1;
        this.xp = 0;
        this.xpExcedente = 0;
        this.pontosDisponiveis = pontosDisponiveis;
        definirArmaPersonagem(arma);
    }
    
    public void preencherAtributos() {
        this.forca = preencherUmAtributo("Força: ", pontosDisponiveis);
        this.poder = preencherUmAtributo("Poder: ", pontosDisponiveis);
        this.destreza = preencherUmAtributo("Destreza: ", pontosDisponiveis);
        this.vitalidade = preencherUmAtributo("Vitalidade: ", pontosDisponiveis);
    }
    
     public int preencherUmAtributo(String atributo, Integer pontosDisponiveis) {   
        while(true) {
            Integer valorAtributo = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos que deseja atribuir para " + atributo));
            if (valorAtributo > 0 && valorAtributo <= pontosDisponiveis) {
                return valorAtributo;
            } else {
                JOptionPane.showMessageDialog(
            null, "O valor do atributo dever ser entre 1 e 10");
            }
        }
    }
    
    public void definirArmaPersonagem(Arma arma) {
        if (arma.getPosicaoArma().equalsIgnoreCase("primária")){
            this.armaPrimaria = arma;
        }
        else {
            this.armaSecundaria = arma;
        }
    }

    public Integer getXpExcedente(){
        return this.xpExcedente;
    }
    public Integer getXp(){
        return this.xp;
    }
    public Integer getPontosDisponiveis(){
        return this.pontosDisponiveis;
    }

    public void setXpExcedente(Integer xpExcedente){
        this.xpExcedente = xpExcedente;
    }
    public void setXp(Integer xp){
        this.xp = xp;
    }
    public void setPontosDisponiveis(Integer pontosDisponiveis){
        this.pontosDisponiveis = pontosDisponiveis;
    }

    public String getNome() {
        return this.nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public Integer getNivel() {
        return this.nivel;
    }
    public Integer getIdade() {
        return this.idade;
    }
    public Integer getForca() {
        return this.forca;
    }
    public Integer getVitalidade() {
        return this.vitalidade;
    }
    public Integer getDestreza() {
        return this.destreza;
    }
    public Integer getPoder() {
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
        if (forca >= 10){
            this.forca = 10;
        }
        else{
            this.forca = forca;
        }
    }
    public void setVitalidade(int vitalidade) {
        if (vitalidade >= 10){
            this.vitalidade = 10;
        }
        else{
            this.vitalidade = vitalidade;
        }
    }
    public void setDestreza(int destreza) {
        if (destreza >= 10){
            this.destreza = 10;
        }
        else{
            this.destreza = destreza;
        }
    }
    public void setPoder(int poder) {
        if (poder >= 10){
            this.poder = 10;
        }
        else{
            this.poder = poder;
        }
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