
/**
 * 
 * @author Adrián Ernesto Cascante Pérez 
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta
{
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;
    
    // Constructor con dos parámetros
    public Cuenta(String nombreCuentaHabiente, double pSaldo){
        cantCuentasCreadas++;; 
        codCuenta = codCuenta + cantCuentasCreadas;
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        fechaCreacion = establecerFechaCreacion();
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;   
    }
    
    // Constructor con solo un parámetro (saldo) 
    public Cuenta (double pSaldo) {
        this("Cuenta sin nombre", pSaldo);
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta(){
        return codCuenta;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public double depositar(double monto) {
        if (monto > 0)
            saldo += monto;
            cantDepositosRealizados++;
        return saldo;
    }
    
    public double retirar(double monto) {
        if (validarRetiro(monto)) 
            saldo -= monto;
            cantRetirosExitososRealizados++;
        return saldo;
    }
    
    private boolean validarRetiro(double monto){
        if (monto > 0 && saldo >= monto)
            return true;
        return false;
    }
    
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    private String establecerFechaCreacion() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
    public String toString(){
        String msg = "";
        msg += "========== Información de la cuenta ==========\n";
        msg += "Código: " + codCuenta + "\n";
        msg += "Cuenta Habiente: " + nombreCuentaHabiente + "\n";
        msg += "Saldo: " + String.format("%.2f", saldo) + "\n";
        msg += "Fecha de creación: " + fechaCreacion + "\n";
        msg += "Depósitos realizados: " + cantDepositosRealizados + "\n";
        msg += "Retiros exitosos: " + cantRetirosExitososRealizados + "\n";
        msg += "=====================================\n";
        return msg;
    }
    
}