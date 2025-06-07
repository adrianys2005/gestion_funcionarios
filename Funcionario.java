package model;

public class Funcionario {
    // Atributos
    private int idFuncionario;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private int idTipoDoc;
    private int idEstadoCivil;
    private String numeroDocumento;
    private String sexo;

    // Constructor vacío
    public Funcionario() {}

    // Constructor parcial (5 parámetros)
    public Funcionario(int id, String nombres, String apellidos, String direccion, String telefono) {
        this.idFuncionario = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor con 7 parámetros (para DAO sin númeroDocumento ni sexo)
    public Funcionario(int idFuncionario, int idTipoDoc, int idEstadoCivil,
                       String nombres, String apellidos, String direccion, String telefono) {
        this.idFuncionario = idFuncionario;
        this.idTipoDoc = idTipoDoc;
        this.idEstadoCivil = idEstadoCivil;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor completo (con todos los atributos)
    public Funcionario(int idFuncionario, int idTipoDoc, int idEstadoCivil,
                       String nombres, String apellidos, String direccion, String telefono,
                       String numeroDocumento, String sexo) {
        this.idFuncionario = idFuncionario;
        this.idTipoDoc = idTipoDoc;
        this.idEstadoCivil = idEstadoCivil;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroDocumento = numeroDocumento;
        this.sexo = sexo;
    }

    // Getters y Setters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

