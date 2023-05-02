package game;

import java.awt.Point;

public class Player extends GameParticipant {
    
    private static Player _instance;
    
    public static Player getInstance() {
        if (_instance == null) {
            _instance = new Player();
        }
        
        return _instance;
    }

    private Player() {
        super(new Point(180, 180), 0);
    }
}
