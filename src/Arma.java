package rpg;
// rapha
public class Arma {
    private int ataqueDefesa;
    private double peso;
    private String tipo;
    private String posicaoArma;
    
    public Arma(int ataqueDefesa, double peso, String tipo, String posicaoArma) {
        this.ataqueDefesa = ataqueDefesa;
        this.peso = peso;
        this.posicaoArma = posicaoArma;
        this.tipo = tipo;
    }
    
    public int getAtaqueDefesa() {
        return this.ataqueDefesa;
    }
    public void setAtaqueDefesa(int ataqueDefesa) {
        this.ataqueDefesa = ataqueDefesa;
    }
    
    public double getPeso() {
        return this.peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String getPosicaoArma() {
        return this.posicaoArma;
    }
    public void setPosicaoArma(String posicaoArma) {
        this.posicaoArma = posicaoArma;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
