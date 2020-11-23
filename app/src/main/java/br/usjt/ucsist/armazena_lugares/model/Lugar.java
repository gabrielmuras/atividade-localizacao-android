package br.usjt.ucsist.armazena_lugares.model;

public class Lugar {


    public String descricao, dataCadastro, latitude, longitude;


    public Lugar(String latitude, String longitude, String descricao, String dataCadastro) {
        this.latitude = latitude;
        this.longitude= longitude;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public Lugar() {
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
