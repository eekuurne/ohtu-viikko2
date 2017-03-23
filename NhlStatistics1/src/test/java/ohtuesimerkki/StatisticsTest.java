package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eekuurne
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void playerSearchFinds() {
        Player player = stats.search("Lemieux");
        
        assertEquals("Lemieux", player.getName());
        assertEquals(45, player.getGoals());
        assertEquals(54, player.getAssists());
    }
    
    @Test
    public void playerSearchDoesntFind() {
        Player player = stats.search("Kuurne");
        
        assertEquals(null, player);
    }
    
    @Test
    public void teamRightSize() {
        List<Player> playersOfTeam = stats.team("EDM");
        
        assertEquals(3, playersOfTeam.size());
    }
    
    @Test
    public void topScorersSize() {
        List<Player> topScorers = stats.topScorers(2);
        
        assertEquals(2, topScorers.size());
    }
    
    @Test
    public void rightTopScorers() {
        List<Player> topScorers = stats.topScorers(2);
        
        assertEquals("Gretzky", topScorers.get(0).getName());
        assertEquals("Lemieux", topScorers.get(1).getName());
    }
}
