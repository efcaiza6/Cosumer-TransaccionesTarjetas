/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.generador.dto;

import lombok.Data;

/**
 *
 * @author bran-
 */
@Data
public class TransaccionRQ {
    
    private String codEstablecimiento;
    private String nroTarjeta;
    private String codVerificacion;
    private int mesExpiracion;
    private int anioExpiracion;
    private String descripcion;
    private String tipo;
    private int meses;
    private float monto;
    
}
