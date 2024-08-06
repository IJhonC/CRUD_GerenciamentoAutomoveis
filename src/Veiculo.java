
//gets e sets para utilização no programa
public class Veiculo {

    private int cod;
    private String chassi;
    private String modelo;
    private String fabricante;
    private int ano;
    private int kmAtual;
    private String motorizacao;
    private String finalPlaca;
    private String cor;
    private String combustivel;
    private String cidade;
    private String carroceria;
    private double valor;
    private String opcionais;
    private String descricao;

    public Veiculo() {

    }

    public Veiculo(String chassi, String modelo, String fabricante, int ano, int kmAtual, String motorizacao,
            String finalPlaca, String cor, String combustivel, String cidade, String carroceria,
            double valor, String opcionais, String descricao) {
        this.chassi = chassi;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.kmAtual = kmAtual;
        this.motorizacao = motorizacao;
        this.finalPlaca = finalPlaca;
        this.cor = cor;
        this.combustivel = combustivel;
        this.cidade = cidade;
        this.carroceria = carroceria;
        this.valor = valor;
        this.opcionais = opcionais;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public String getMotorizacao() {
        return motorizacao;
    }

    public void setMotorizacao(String motorizacao) {
        this.motorizacao = motorizacao;
    }

    public String getFinalPlaca() {
        return finalPlaca;
    }

    public void setFinalPlaca(String finalPlaca) {
        this.finalPlaca = finalPlaca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(String opcionais) {
        this.opcionais = opcionais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
