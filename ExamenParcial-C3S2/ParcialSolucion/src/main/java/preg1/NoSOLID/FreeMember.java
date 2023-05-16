package preg1.NoSOLID;

public class FreeMember extends Member{
    public FreeMember(String nombre){
        super(nombre);
    }
    @Override
    public void joinTournament(){
        System.out.println("El miembro free" + this.nombre + "se une al torneo");
    }
    //Este método rompe el LSP
    @Override
    public void organizeTournament(){
        throw new UnsupportedOperationException();
    }
}
