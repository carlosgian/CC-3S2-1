package preg1.NoSOLID;

import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Version sin LSP");
        List<Member> miembros;
        miembros = List.of(new PremiunMember("Abejita azul"), new VipMember("Kaperucita Feliz"), new FreeMember("Inspectora Motita") );

        for (Member miembro: miembros){
            miembro.joinTournament();
            miembro.organizeTournament();
        }
    }
}
