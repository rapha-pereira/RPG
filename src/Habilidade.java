package rpg;

public class Habilidade {
    private String nome, descricao, tipoAtributo;
    private int poderMinimo;
    
    public Habilidade(String nome, String descricao, String tipoAtributo, int poderMinimo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoAtributo = tipoAtributo;
        this.poderMinimo = poderMinimo;
    }
    
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getTipoAtributo() {
        return this.tipoAtributo;
    }
    public void setTipoAtributo(String tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }
    
    public int getPoderMinimo() {
        return this.poderMinimo;
    }
    public void setPoderMinimo(int poderMinimo) {
        this.poderMinimo = poderMinimo;
    }
    
}