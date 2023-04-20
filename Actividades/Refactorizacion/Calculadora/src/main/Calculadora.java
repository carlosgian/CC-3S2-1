package main;

public class Calculadora {

    private boolean status;
    public Calculadora(){
        this.status = true;
    }

    public boolean getStatus(){
        return status;
    }

    public int addition(int a, int b){
        return a + b;
    }

    public int subtraction(int a, int b){
        return a -b;
    }

    public int division(int a, int b){
        if ( b == 0 ){
            throw new IllegalArgumentException("No se puede dividir por cero");
        } else {
            return a / b;
        }
    }

//NUEVOS MÉTODOS EMPIEZAN AQUÍ
    public int multiply(int a, int b){
        return a*b;
    }

    public int remainder(int a, int b){
        if ( b == 0 ){
            throw new IllegalArgumentException("No se puede calcular el residuo con cero");
        } else {
            return a % b;
        }
    }

    public int exponentiation(int a, int b){
        if ( b < 0 ){
            throw new IllegalArgumentException("No se permiten exponentes negativos en el dominio de los enteros");
        } else{
            int result = 1;
            for(int i = 0; i < b; i++){
                result = result*a;
            }
            return result;
        }
    }
}
