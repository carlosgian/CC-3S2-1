package preg1.SOLID;

public class VipMember implements MemberJoin,MemberOrganizer {
    private String nombre;

    public VipMember(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void joinTournament() {
        System.out.println("El miembro Vip " + nombre + " se une al torneo.");
    }

    @Override
    public void organizeTournament() {
        System.out.println("El miembro Vip " + nombre + " organiza al torneo.");
    }
}
