package preg1.SOLID;

public class FreeMember implements MemberJoin {
    private String nombre;

    public FreeMember(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void joinTournament() {
        System.out.println("El miembro Premiun " + nombre + " se une al torneo.");
    }
}
