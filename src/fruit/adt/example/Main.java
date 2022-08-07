package fruit.adt.example;

record FruitSalad(
    AppleVariety apple,
    BananaVariety banana,
    CherryVariety cherries
) { }

sealed interface FruitSnack permits Apple, Banana, Cherries { }
record Apple(AppleVariety variety) implements FruitSnack { }
record Banana(BananaVariety variety) implements FruitSnack { }
record Cherries(CherryVariety variety) implements FruitSnack { }

enum AppleVariety {GOLDEN_DELICIOUS, GRANNY_SMITH, FUJI}
enum BananaVariety {CAVENDISH, GROS_MICHEL, MANZANO}
enum CherryVariety {MONTMORENCY, BING}

public class Main {

  String pickySnackerRemark(FruitSnack snack) {
    return switch (snack) {
      case Apple(var variety) when variety.equals(AppleVariety.FUJI) ->
        "That's my favourite apple.";
      case Apple(var variety) when variety.equals(AppleVariety.GOLDEN_DELICIOUS) ->
        "I can't stand Golden Delicious apples.";
      case Banana(var variety) when variety.equals(BananaVariety.CAVENDISH) ->
        "That's my favourite banana.";
      case Banana(var variety) when variety.equals(BananaVariety.MANZANO) ->
        "Manzano is my least favourite banana.";
      case Cherries(var variety) when variety.equals(CherryVariety.BING) ->
        "Those are my favourite cherries.";
      default ->
          "It will do.";
    };
  }

  String pickyCustomerReaction(FruitSalad salad) {
    return switch (salad) {
      case FruitSalad(var apple, var banana , var cherries)
        when apple.equals(AppleVariety.FUJI) && banana.equals(BananaVariety.CAVENDISH) && cherries.equals(CherryVariety.BING) ->
          "That's my favourite combination.";
      case FruitSalad(var apple, var banana , var cherries)
        when apple.equals(AppleVariety.GOLDEN_DELICIOUS) ->
          "I can't stand Golden Delicious apples.";
      case FruitSalad(var apple, var banana , var cherries)
        when banana.equals(BananaVariety.MANZANO) ->
          "Manzano is my least favourite banana.";
      case FruitSalad(var apple, var banana , var cherries)
        when cherries.equals(CherryVariety.BING) ->
          "Bing are my favourite cherries.";
      case FruitSalad(var apple, var banana , var cherries)
        when banana.equals(BananaVariety.MANZANO) && cherries.equals(CherryVariety.BING) ->
          "I both love and hate this.";
      default -> "It will do.";
    };
  }

  public static void main(String[] args) {

    var salad = new FruitSalad(AppleVariety.GOLDEN_DELICIOUS, BananaVariety.CAVENDISH, CherryVariety.MONTMORENCY);
    var sameSalad = new FruitSalad(AppleVariety.GOLDEN_DELICIOUS, BananaVariety.CAVENDISH, CherryVariety.MONTMORENCY);
    var differentSalad = new FruitSalad(AppleVariety.GOLDEN_DELICIOUS, BananaVariety.MANZANO, CherryVariety.MONTMORENCY);

    var snack = new Apple(AppleVariety.GOLDEN_DELICIOUS);
    var sameSnack = new Apple(AppleVariety.GOLDEN_DELICIOUS);
    var differentSnack = new Banana(BananaVariety.CAVENDISH);

    assert (salad.toString().equals("FruitSalad[apple=GOLDEN_DELICIOUS, banana=CAVENDISH, cherries=MONTMORENCY]"));
    assert (salad.equals(sameSalad));
    assert (!salad.equals(differentSalad));

    assert (snack.toString().equals("Apple[variety=GOLDEN_DELICIOUS]"));
    assert (snack.equals(sameSnack));
    assert (!snack.equals(differentSnack));

    assert (!salad.equals(snack));
    assert (!salad.equals(AppleVariety.GOLDEN_DELICIOUS));
    assert (!snack.equals(AppleVariety.GOLDEN_DELICIOUS));

  }
}
