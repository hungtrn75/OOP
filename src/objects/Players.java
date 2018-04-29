
package objects;


public class Players {
    private String pName;
    private int pTurn;
    private int pRoundScore;
    private int pTotalScore;
    
    public void setName(String name){
        this.pName = name;
    }
    public String getName(){
        return this.pName;
    }
    public int getPTurn(){
        return this.pTurn;
    }
    public void setPTurn(int turn){
        this.pTurn = turn;
    }
    public void setRoundScore(int score){
        this.pRoundScore = score;
    }
    public int getRoundScore(){
        return this.pRoundScore;
    }
    public void setTotalScore(int score){
        this.pTotalScore = score;
    }
    public int getTotalScore(){
        return this.pTotalScore;
    }
}
