package br.usjt.ucsist.armazena_lugares.model;

public class Lugar {


    public String endereco, descricao, dataCadastro, latitude, longitude;


    public Lugar(String endereco, String latitude, String longitude, String descricao, String dataCadastro) {

        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude= longitude;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public Lugar() {
    }
    public String getEndereco() {
        return endereco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLongitude() {
        return longitude;
    }
}
