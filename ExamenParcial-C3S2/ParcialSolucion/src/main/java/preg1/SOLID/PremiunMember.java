package preg1.SOLID;

public class PremiunMember implements MemberJoin,MemberOrganizer {
    private String nombre;

    public PremiunMember(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void joinTournament() {
        System.out.println("El miembro Premiun " + nombre + " se une al torneo.");
    }

    @Override
    public void organizeTournament() {
        System.out.println("El miembro Premiun " + nombre + " organiza al torneo.");
    }
}
