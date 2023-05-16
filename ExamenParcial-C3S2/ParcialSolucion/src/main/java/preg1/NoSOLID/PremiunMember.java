package preg1.NoSOLID;

public class PremiunMember extends Member{
    public PremiunMember(String nombre){
        super(nombre);
    }
    @Override
    public void joinTournament(){
        System.out.println("El miembro Premiun " + this.nombre + " se une al torneo");
    }
    public void organizeTournament(){
        System.out.println("El miembro Premiun " + this.nombre + " organiza un torneo.");
    }

}
