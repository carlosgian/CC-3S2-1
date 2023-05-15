package preg1.NoSOLID;

public abstract class Member {
    protected final String nombre;
    public Member(String nombre){
        this.nombre = nombre;
    }
     public abstract void joinTournament();
     public abstract void organizeTournament();
}
