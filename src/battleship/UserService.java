package battleship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService  {

    private boolean userMove;

    private Map<String, List<BattleshipFields>> userShips = new HashMap<>();

    public UserService() {
    }

    public boolean isUserMove() {
        return userMove;
    }

    public void setUserMove(boolean userMove) {
        this.userMove = userMove;
    }

    public Map<String, List<BattleshipFields>> getUserShips() {
        return userShips;
    }


    public boolean isShipSank (String providedField){

      return  userShips.get(providedField).stream().allMatch(BattleshipFields::isFieldDown);

    }










}

