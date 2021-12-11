package Day10;

public class Bracket {
  private final char pushCharacter;
  private final char popCharacter;
  private final int corruptedValue;
  private final int autoCompleteValue;

  public Bracket(char pushCharacter, char popCharacter, int corruptedValue, int autoCompleteValue) {
    this.pushCharacter = pushCharacter;
    this.popCharacter = popCharacter;
    this.corruptedValue = corruptedValue;
    this.autoCompleteValue = autoCompleteValue;
  }

  public char getPushCharacter() {
    return pushCharacter;
  }

  public char getPopCharacter() {
    return popCharacter;
  }

  public int getCorruptedValue() {
    return corruptedValue;
  }

  public int getAutoCompleteValue() {
    return autoCompleteValue;
  }

}
