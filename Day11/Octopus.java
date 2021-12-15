package Day11;

public class Octopus {
  int value;
  boolean flashed;

  public Octopus(int value) {
    this.value = value;
    this.flashed = false;
  }

  private Octopus(int value, boolean flashed) {
    this.value = value;
    this.flashed = flashed;
  }

  public Octopus copy() {
    return new Octopus(this.value, this.flashed);
  }

}
