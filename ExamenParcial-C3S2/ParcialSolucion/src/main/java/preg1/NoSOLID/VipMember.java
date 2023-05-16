package preg1.NoSOLID;

public class VipMember extends Member{
    public VipMember(String nombre){
        super(nombre);
    }
    @Override
    public void joinTournament(){
        System.out.println("El miembro VIP " + this.nombre + " se une al torneo.");
    }
    public void organizeTournament(){
        System.out.println("El miembro VIP " + this.nombre + " organiza un torneo.");
    }
}
