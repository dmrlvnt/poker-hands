package com.casino.carddealer.evaluator;

import static com.casino.carddealer.utility.Constants.NUMBER_OF_ONE_HAND;
import static com.casino.carddealer.utility.Constants.CARD_PATTERN;

import com.casino.carddealer.comparator.EvaluationComparator;
import com.casino.carddealer.utility.LoggerHelper;
import com.casino.carddealer.validator.ErrorCodes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is used to evaluate the given hands by creating users, inserting their cards and
 * calling Rank finder {@link ErrorCodes} to generate teh evaluations. Finally, it prints out the
 * game result with all information.
 */
public class GameEvaluator {

  private static final Logger log = LoggerHelper.createLogger(GameEvaluator.class.getName());
  private final Map<String, List<String>> deck = new HashMap<>();

  public GameEvaluator(List<String> hands) {
    populateCards(hands);
  }

  public void evaluate() {
    List<Evaluation> evaluations = deck.entrySet().stream().map(RankFinder::find)
        .sorted(new EvaluationComparator())
        .collect(Collectors.toList());

    var winners = new StringJoiner(", ");

    log.info("Ranking:");

    var rank = 1;
    for (Evaluation ev : evaluations) {
      String player = ev.getPlayer();
      if (ev.isEqualPrevious()) {
        // reduce the rank because of equality.
        rank--;
      }
      if (rank == 1) {
        // add another winner.
        winners.add(player);
      }

      String hand = ev.getHand().replaceAll(CARD_PATTERN, "$0 ");
      String handRank = ev.getRank().toString() + ", " + ev.getHandOf();
      final int order = rank;
      log.log(Level.INFO, () ->
          String.format("\t%s\t%s\t%s\t%s", order, player, hand, handRank));
      rank++;
    }

    String winnerMessage =
        "\n" + winners + (winners.toString().contains(", ") ? " win." : " wins.");
    log.info(winnerMessage);
  }

  /**
   * Populate the deck with users and their cards.
   */
  private void populateCards(List<String> cards) {
    var players = IntStream.rangeClosed(1, cards.size() / NUMBER_OF_ONE_HAND);
    players.forEach(p -> deck
        .put("Player " + p, cards.subList((p - 1) * NUMBER_OF_ONE_HAND, p * NUMBER_OF_ONE_HAND)));
  }

}
